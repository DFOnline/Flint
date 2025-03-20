package dev.dfonline.flint.feature.trait;

import net.minecraft.network.packet.Packet;

public interface PacketListeningFeature extends FeatureTrait {

    PacketResult onReceivePacket(Packet<?> packet);

    PacketResult onSendPacket(Packet<?> packet);

    enum PacketResult {
        CANCEL,
        PASS
    }

}
