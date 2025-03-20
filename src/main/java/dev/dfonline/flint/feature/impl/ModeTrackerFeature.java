package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import net.minecraft.network.packet.Packet;

public class ModeTrackerFeature implements PacketListeningFeature {

    @Override
    public PacketResult onReceivePacket(Packet<?> packet) {
        return PacketResult.PASS;
    }

    @Override
    public PacketResult onSendPacket(Packet<?> packet) {
        return PacketResult.PASS;
    }

}
