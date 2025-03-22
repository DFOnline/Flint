package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.packet.Packet;

public interface PacketListeningFeature extends FeatureTrait {

    default EventResult onReceivePacket(Packet<?> packet) {
        return EventResult.PASS;
    }

    default EventResult onSendPacket(Packet<?> packet) {
        return EventResult.PASS;
    }

}
