package dev.dfonline.flint.feature.trait;

import net.minecraft.network.packet.Packet;

public interface PacketListeningFeature extends FeatureTrait {

    default Result onReceivePacket(Packet<?> packet) {
        return Result.PASS;
    }
    default Result onSendPacket(Packet<?> packet) {
        return Result.PASS;
    }
}
