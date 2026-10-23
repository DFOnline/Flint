package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.ConnectionListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.hypercube.PlotSize;
import dev.dfonline.flint.util.FlintUpdate;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundClearTitlesPacket;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.regex.Pattern;

/**
 * Handles tracking the player's mode and updating it accordingly.
 */
public class ModeTrackerFeature implements PacketListeningFeature, TickedFeature, ConnectionListeningFeature {

    private static final Logger LOGGER = Logger.of(ModeTrackerFeature.class);
    private static final Pattern SPAWN_ACTION_BAR_PATTERN =
            Pattern.compile("(⏵+ - )?⧈ -?\\d+ Tokens {2}ᛥ -?\\d+ Tickets {2}⚡ -?\\d+ Sparks");
    private static final String DEV_MODE_MESSAGE = "» You are now in dev mode.";
    private static final String BUILD_MODE_MESSAGE = "» You are now in build mode.";
    private static final String JOINED_GAME_PREFIX = "» Joined game: ";
    private static final int DEV_SPAWN_OFFSET = 10;
    private static final int GROUND_LEVEL = 49;

    private PendingModeSwitchAction pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
    private static boolean hasQueuedLocate = false;
    private static Mode queuedMode = null;
    private static boolean sentUpdateMessageThisSession = false;

    @Override
    public boolean alwaysOn() {
        return true;
    }

    private static void setMode(Mode mode) {
        if (FlintAPI.isDebugging()) {
            LOGGER.info("Setting to mode " + mode);
        }

        if (FlintAPI.shouldConfirmLocationWithLocate() && mode != Mode.NONE) {
            hasQueuedLocate = true;
        } else {
            Flint.getUser().setNode(null);
            Flint.getUser().setPlot(null);
            Flint.getUser().setMode(mode);
        }
    }

    @Override
    public EventResult onReceivePacket(Packet<?> packet) {
        if (!hasQueuedLocate) {
            if (packet instanceof ClientboundClearTitlesPacket clear && clear.shouldResetTimes()) {
                this.pendingAction = PendingModeSwitchAction.POSITION_CHANGE;
            } else if (packet instanceof ClientboundSetDefaultSpawnPositionPacket &&
                    this.pendingAction == PendingModeSwitchAction.POSITION_CHANGE) {
                this.pendingAction = PendingModeSwitchAction.MESSAGE;
            }
        }

        boolean overlayMatches = packet instanceof ClientboundSetActionBarTextPacket(Component text) &&
                this.pendingAction == PendingModeSwitchAction.MESSAGE &&
                SPAWN_ACTION_BAR_PATTERN.matcher(text.getString()).matches();

        if (overlayMatches) {
            queuedMode = Mode.SPAWN;
            this.pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
        }

        if (!hasQueuedLocate &&
                packet instanceof ClientboundSystemChatPacket gameMsg &&
                this.pendingAction == PendingModeSwitchAction.MESSAGE) {
            String content = gameMsg.content().getString();

            if (content.equals(DEV_MODE_MESSAGE)) {
                setMode(Mode.DEV);
            } else if (content.equals(BUILD_MODE_MESSAGE)) {
                setMode(Mode.BUILD);
            } else if (content.startsWith(JOINED_GAME_PREFIX)) {
                setMode(Mode.PLAY);
            }
        }

        return EventResult.PASS;
    }

    @Override
    public void tick() {
        if (Flint.getClient().player != null) {
            if (hasQueuedLocate) {
                hasQueuedLocate = false;
                String name = Flint.getUser().getPlayer().getScoreboardName();
                LocateFeature.requestLocate(name).thenAccept(locate -> {
                    Flint.getUser().setNode(locate.node());
                    Flint.getUser().setNodeId(locate.nodeId());

                    Vec3i newOrigin;
                    if (locate.mode() == Mode.DEV) {
                        BlockPos blockpos = Flint.getUser().getPlayer().blockPosition();
                        newOrigin = new Vec3i(blockpos.getX() + DEV_SPAWN_OFFSET, GROUND_LEVEL, blockpos.getZ() - DEV_SPAWN_OFFSET);
                    } else {
                        newOrigin = null;
                    }

                    Plot currentPlot = Flint.getUser().getPlot();

                    if (locate.plot() != null) {
                        if (currentPlot == null || !currentPlot.equals(locate.plot())) {
                            Flint.getUser().setPlot(locate.plot());
                        }
                        if (Flint.getUser().getPlot().getDevOrigin() == null && newOrigin != null) {
                            Flint.getUser().getPlot().setDevOrigin(newOrigin);
                        }
                    } else {
                        Flint.getUser().setPlot(null);
                    }
                    Flint.getUser().setMode(locate.mode());
                });
            }

            if (queuedMode != null) {
                if (!sentUpdateMessageThisSession) {

                    FlintUpdate.sendUpdateMessage();

                    sentUpdateMessageThisSession = true;
                }
                setMode(queuedMode);
                queuedMode = null;
            }

            Plot plot = Flint.getUser().getPlot();
            if (plot != null && plot.getDevOrigin() != null) {
                if (!plot.isSizeKnown()) {
                    plot.setSize(detectPlotSize());
                }

                plot.setHasUnderground(detectPlotUnderground());
            }
        }
    }

    @Override
    public void onDisconnect() {
        setMode(Mode.NONE);
        sentUpdateMessageThisSession = false;
    }

    private enum PendingModeSwitchAction {
        CLEAR_TITLE,
        POSITION_CHANGE,
        MESSAGE
    }

    private PlotSize detectPlotSize() {
        if (Flint.getUser().getMode() != Mode.DEV || Flint.getUser().getPlot() == null || Flint.getUser().getPlot().getDevOrigin() == null) {
            return null;
        }

        Plot plot = Flint.getUser().getPlot();
        Vec3i devOrigin = plot.getDevOrigin();

        BlockPos pos = new BlockPos(devOrigin.getX() - 1, 49, devOrigin.getZ());
        ClientLevel world = Flint.getClient().level;
        if (world == null) return null;

        BlockState BASIC = world.getBlockState(pos.south(50));
        BlockState BASIC_PLUS = world.getBlockState(pos.south(51));
        BlockState LARGE = world.getBlockState(pos.south(100));
        BlockState LARGE_PLUS = world.getBlockState(pos.south(101));
        BlockState MASSIVE = world.getBlockState(pos.south(300));
        BlockState MASSIVE_PLUS = world.getBlockState(pos.south(301));
        BlockState MEGA = world.getBlockState(pos.offset(-18, 0, 10));
        BlockState MEGA_PLUS = world.getBlockState(pos.offset(-19, 0, 10));

        if (MEGA_PLUS.is(Blocks.GRASS_BLOCK) && MEGA.is(Blocks.GRASS_BLOCK)) {
            return PlotSize.MEGA;
        } else if (!MEGA.is(Blocks.VOID_AIR) && !MEGA_PLUS.is(Blocks.VOID_AIR) && !MEGA.is(Blocks.GRASS_BLOCK) && !MEGA.is(Blocks.STONE) && !MEGA_PLUS.is(Blocks.GRASS_BLOCK)) {
            return PlotSize.MEGA;
        } else if (!(BASIC.is(Blocks.VOID_AIR) || BASIC_PLUS.is(Blocks.VOID_AIR)) && !BASIC.is(BASIC_PLUS.getBlock())) {
            return PlotSize.BASIC;
        } else if (!(LARGE.is(Blocks.VOID_AIR) || LARGE_PLUS.is(Blocks.VOID_AIR)) && !LARGE.is(LARGE_PLUS.getBlock())) {
            return PlotSize.LARGE;
        } else if (!(MASSIVE.is(Blocks.VOID_AIR) || MASSIVE_PLUS.is(Blocks.VOID_AIR)) && !MASSIVE.is(MASSIVE_PLUS.getBlock())) {
            return PlotSize.MASSIVE;
        } else {
            // unknown, maybe the world is still streaming in chunks
            return null;
        }
    }

    private boolean detectPlotUnderground() {
        Plot plot = Flint.getUser().getPlot();

        if (plot == null) return false;
        if (Flint.getClient().level == null) return false;

        PlotSize size = plot.getSize();
        BlockState groundCheck = Flint.getClient().level.getBlockState(new BlockPos(
                Math.max(Math.min((int) Flint.getUser().getPlayer().getX(), plot.getDevOrigin().getX() - 1), plot.getDevOrigin().getX() - (size.getCodeWidth())),
                49,
                Math.max(Math.min((int) Flint.getUser().getPlayer().getZ(), plot.getDevOrigin().getZ() + size.getCodeLength()), plot.getDevOrigin().getZ())
        ));

        if (!groundCheck.is(Blocks.VOID_AIR)) {
            return !groundCheck.is(Blocks.GRASS_BLOCK) && !groundCheck.is(Blocks.STONE);
        } else {
            return false;
        }
    }

}
