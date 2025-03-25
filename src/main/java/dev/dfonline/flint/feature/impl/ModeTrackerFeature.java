package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.hypercube.Mode;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ModeTrackerFeature implements PacketListeningFeature, TickedFeature {

    private static final Logger LOGGER = Logger.of(ModeTrackerFeature.class);
    private static final double DEV_SPAWN_OFFSET = 10.5;
    private PendingModeSwitchAction pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
    private static boolean hasQueuedLocate = false;

    private static void setMode(Mode mode) {
        final Vec3d newOrigin;
        if (mode == Mode.DEV) {
            Vec3d devPos = Flint.getUser().getPlayer().getPos();
            newOrigin = new Vec3d(devPos.x + DEV_SPAWN_OFFSET, 0, devPos.z - DEV_SPAWN_OFFSET);
        } else {
            newOrigin = null;
        }
        if (FlintAPI.isDebugging()) {
            LOGGER.info("Setting to mode " + mode);
            LOGGER.info("Player: " + Flint.getUser().getPlayer());
        }
        if (FlintAPI.shouldConfirmLocationWithLocate() && Flint.getUser().getPlayer() != null) {
            LocateFeature.requestLocate(Flint.getUser().getPlayer().getNameForScoreboard()).thenAccept(locate -> {
                Flint.getUser().setNode(locate.node());
                Plot playerPlot = Flint.getUser().getPlot();

                if (locate.plot() != null) {
                    if (playerPlot == null || !playerPlot.equals(locate.plot())) {
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
        } else {
            Flint.getUser().setNode(null);
            Flint.getUser().setPlot(null);
            Flint.getUser().setMode(mode);
        }
    }

    @Override
    public EventResult onReceivePacket(Packet<?> packet) {

        if (!hasQueuedLocate && packet instanceof ClearTitleS2CPacket clear && clear.shouldReset()) {
            this.pendingAction = PendingModeSwitchAction.POSITION_CHANGE;
        }

        if (!hasQueuedLocate && packet instanceof PlayerSpawnPositionS2CPacket && this.pendingAction == PendingModeSwitchAction.POSITION_CHANGE) {
            this.pendingAction = PendingModeSwitchAction.MESSAGE;
        }

        if (!hasQueuedLocate && packet instanceof OverlayMessageS2CPacket(Text text)
                && this.pendingAction == PendingModeSwitchAction.MESSAGE
                && text.getString().matches("(⏵+ - )?⧈ -?\\d+ Tokens {2}ᛥ -?\\d+ Tickets {2}⚡ -?\\d+ Sparks")) {
            setMode(Mode.SPAWN);
            this.pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
        }

        if (!hasQueuedLocate && packet instanceof GameMessageS2CPacket message) {
            if (this.pendingAction == PendingModeSwitchAction.MESSAGE) {
                String content = message.content().getString();
                if (content.equals("» You are now in dev mode.")) {
                    setMode(Mode.DEV);
                }

                if (content.equals("» You are now in build mode.")) {
                    setMode(Mode.BUILD);

                }

                if (content.startsWith("» Joined game: ")) {
                    setMode(Mode.PLAY);
                }
            }
        }

        if (packet instanceof GameJoinS2CPacket) {
            hasQueuedLocate = true;
        }

        return EventResult.PASS;
    }

    @Override
    public void tick() {
        if (Flint.getClient().player != null && hasQueuedLocate) {
            hasQueuedLocate = false;
            LocateFeature.requestLocate(Flint.getUser().getPlayer().getNameForScoreboard()).thenAccept(locate -> {
                Flint.getUser().setNode(locate.node());
                Flint.getUser().setPlot(locate.plot());
                Flint.getUser().setMode(locate.mode());
            });
        }
    }

    private enum PendingModeSwitchAction {
        CLEAR_TITLE,
        POSITION_CHANGE,
        MESSAGE
    }

}
