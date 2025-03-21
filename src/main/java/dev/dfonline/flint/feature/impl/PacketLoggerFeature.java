package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.Result;
import net.minecraft.network.packet.Packet;

import java.util.List;

public class PacketLoggerFeature implements PacketListeningFeature {

    private static final Logger LOGGER = Logger.of(PacketLoggerFeature.class);

    @Override
    public Result onReceivePacket(Packet<?> packet) {
        if (!FlintAPI.isDebugging()) {
            return Result.PASS;
        }

        if (List.of("ChunkDataS2CPacket", "OverlayMessageS2CPacket", "UnloadChunkS2CPacket", "WorldTimeUpdateS2CPacket", "KeepAliveS2CPacket", "MapUpdateS2CPacket", "PlayerListS2CPacket").contains(packet.getClass().getSimpleName())) {
            return Result.PASS;
        }

        LOGGER.info("[v] " + packet.getClass().getSimpleName());

        return Result.PASS;
    }

    @Override
    public Result onSendPacket(Packet<?> packet) {
        if (!FlintAPI.isDebugging()) {
            return Result.PASS;
        }

        String packetName = packet.getClass().getSimpleName();

        if (List.of("ClientTickEndC2SPacket", "KeepAliveC2SPacket", "PositionAndOnGround", "LookAndOnGround", "Full", "PlayerInputC2SPacket").contains(packetName)) {
            return Result.PASS;
        }

        LOGGER.info("[^] " + packetName);

        return Result.PASS;
    }

}
