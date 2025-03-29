package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.ConnectionListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.regex.Pattern;

/**
 * Handles tracking the player's mode and updating it accordingly.
 */
public class ModeTrackerFeature
        implements PacketListeningFeature, TickedFeature, ConnectionListeningFeature {

    private static final Logger LOGGER = Logger.of(ModeTrackerFeature.class);
    private static final double DEV_SPAWN_OFFSET = 10.5;
    private static final Pattern SPAWN_ACTION_BAR_PATTERN =
            Pattern.compile("(⏵+ - )?⧈ -?\\d+ Tokens {2}ᛥ -?\\d+ Tickets {2}⚡ -?\\d+ Sparks");
    private static final String DEV_MODE_MESSAGE = "» You are now in dev mode.";
    private static final String BUILD_MODE_MESSAGE = "» You are now in build mode.";
    private static final String JOINED_GAME_PREFIX = "» Joined game: ";

    private PendingModeSwitchAction pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
    private static boolean hasQueuedLocate = false;
    private static Mode queuedMode = null;

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
            if (packet instanceof ClearTitleS2CPacket clear && clear.shouldReset()) {
                this.pendingAction = PendingModeSwitchAction.POSITION_CHANGE;
            } else if (packet instanceof PlayerSpawnPositionS2CPacket &&
                    this.pendingAction == PendingModeSwitchAction.POSITION_CHANGE) {
                this.pendingAction = PendingModeSwitchAction.MESSAGE;
            }
        }

        boolean overlayMatches = packet instanceof OverlayMessageS2CPacket(Text text) &&
                this.pendingAction == PendingModeSwitchAction.MESSAGE &&
                SPAWN_ACTION_BAR_PATTERN.matcher(text.getString()).matches();

        if (overlayMatches) {
            queuedMode = Mode.SPAWN;
            this.pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
        }

        if (!hasQueuedLocate &&
                packet instanceof GameMessageS2CPacket gameMsg &&
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
                //Flint.getClient().player.sendMessage(literal("Queued Locate Be Getting Processed dayum"), false);
                hasQueuedLocate = false;
                Vec3d newOrigin;
                if (Flint.getUser().getMode() == Mode.DEV) {
                    Vec3d playerPos = Flint.getUser().getPlayer().getPos();
                    newOrigin = new Vec3d(playerPos.x + DEV_SPAWN_OFFSET, 0, playerPos.z - DEV_SPAWN_OFFSET);
                } else {
                    newOrigin = null;
                }
                String name = Flint.getUser().getPlayer().getNameForScoreboard();
                LocateFeature.requestLocate(name).thenAccept(locate -> {
                    Flint.getUser().setNode(locate.node());
                    Plot currentPlot = Flint.getUser().getPlot();

                    if (locate.plot() != null) {
                        if (currentPlot == null || !currentPlot.equals(locate.plot())) {
                            Flint.getUser().setPlot(locate.plot());
                        }
                        if (Flint.getUser().getPlot().getOrigin() == null && newOrigin != null) {
                            Flint.getUser().getPlot().setOrigin(newOrigin);
                        }
                    } else {
                        Flint.getUser().setPlot(null);
                    }
                    Flint.getUser().setMode(locate.mode());
                });
            }
            if (queuedMode != null) {
                setMode(queuedMode);
                queuedMode = null;
            }
        }
    }

    @Override
    public void onDisconnect() {
        setMode(Mode.NONE);
    }

    private enum PendingModeSwitchAction {
        CLEAR_TITLE,
        POSITION_CHANGE,
        MESSAGE
    }

}
