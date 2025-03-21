package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.hypercube.Mode;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ClearTitleS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;
import net.minecraft.text.Text;

public class ModeTrackerFeature implements PacketListeningFeature {

    private PendingModeSwitchAction pendingAction = PendingModeSwitchAction.CLEAR_TITLE;

    private static void setMode(Mode mode) {
        Flint.getUser().setPlot(null);
        Flint.getUser().setMode(mode);

        if (!FlintAPI.shouldConfirmLocationWithLocate()) {
            return;
        }

        LocateFeature.requestLocate(Flint.getUser().getPlayer().getNameForScoreboard()).thenAccept(locate -> {
            Flint.getUser().setPlot(locate.plot());
            Flint.getUser().setMode(locate.mode());
        });
    }

    @Override
    public PacketResult onReceivePacket(Packet<?> packet) {

        if (packet instanceof ClearTitleS2CPacket clear && clear.shouldReset()) {
            this.pendingAction = PendingModeSwitchAction.POSITION_CHANGE;
        }

        if (packet instanceof PlayerSpawnPositionS2CPacket && this.pendingAction == PendingModeSwitchAction.POSITION_CHANGE) {
            this.pendingAction = PendingModeSwitchAction.MESSAGE;
        }

        if (packet instanceof OverlayMessageS2CPacket(Text text)
                && this.pendingAction == PendingModeSwitchAction.MESSAGE
                && text.getString().matches("(⏵+ - )?⧈ -?\\d+ Tokens {2}ᛥ -?\\d+ Tickets {2}⚡ -?\\d+ Sparks")) {
            setMode(Mode.SPAWN);
            this.pendingAction = PendingModeSwitchAction.CLEAR_TITLE;
        }

        if (packet instanceof GameMessageS2CPacket message) {
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
            Flint.getUser().setMode(Mode.SPAWN);
            Flint.getUser().setPlot(null);
        }

        return PacketResult.PASS;
    }

    @Override
    public PacketResult onSendPacket(Packet<?> packet) {
        return PacketResult.PASS;
    }

    private enum PendingModeSwitchAction {
        CLEAR_TITLE,
        POSITION_CHANGE,
        MESSAGE
    }

}
