package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.packet.Packet;

/**
 * A feature that listens for packets.
 */
public interface PacketListeningFeature extends FeatureTrait {

    /**
     * Gets called when a packet is received.
     *
     * @param packet The packet that was received
     * @return The result of the event
     */
    default EventResult onReceivePacket(Packet<?> packet) {
        return EventResult.PASS;
    }

    /**
     * Gets called when a packet is sent.
     *
     * @param packet The packet that was sent
     * @return The result of the event
     */
    default EventResult onSendPacket(Packet<?> packet) {
        return EventResult.PASS;
    }

}
