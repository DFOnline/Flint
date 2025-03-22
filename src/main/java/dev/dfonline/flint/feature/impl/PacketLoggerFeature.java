package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.packet.Packet;

import java.util.List;

public class PacketLoggerFeature implements PacketListeningFeature {

    private static final Logger LOGGER = Logger.of(PacketLoggerFeature.class);

    @Override
    public boolean isEnabled() {
        return FlintAPI.isDebugging();
    }

    @Override
    public EventResult onReceivePacket(Packet<?> packet) {

        if (List.of("ChunkDataS2CPacket", "OverlayMessageS2CPacket", "UnloadChunkS2CPacket", "WorldTimeUpdateS2CPacket", "KeepAliveS2CPacket", "MapUpdateS2CPacket", "PlayerListS2CPacket").contains(packet.getClass().getSimpleName())) {
            return EventResult.PASS;
        }

        LOGGER.info("[v] " + packet.getClass().getSimpleName());

        return EventResult.PASS;
    }

    @Override
    public EventResult onSendPacket(Packet<?> packet) {

        String packetName = packet.getClass().getSimpleName();

        if (List.of("ClientTickEndC2SPacket", "KeepAliveC2SPacket", "PositionAndOnGround", "LookAndOnGround", "Full", "PlayerInputC2SPacket").contains(packetName)) {
            return EventResult.PASS;
        }

        LOGGER.info("[^] " + packetName);

        return EventResult.PASS;
    }

}
