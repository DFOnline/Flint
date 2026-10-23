package dev.dfonline.flint.feature.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.util.RateLimiter;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import net.minecraft.network.protocol.game.ServerboundChatPacket;

import java.util.ArrayDeque;

/**
 * Queues up commands and sends while avoiding getting kicked for spam.
 */
public final class CommandSenderFeature implements PacketListeningFeature, TickedFeature {

    // Vanilla Minecraft uses an increment of 20 and a threshold of 200.
    // We have a lower threshold for extra safety and to account for lag.
    private static final RateLimiter rateLimiter = new RateLimiter(20, 160);
    private static final ArrayDeque<String> commandQueue = new ArrayDeque<>();

    public static void queue(String command) {
        commandQueue.add(command);
    }

    public static void clearQueue() {
        commandQueue.clear();
    }

    public static int queueSize() {
        return commandQueue.size();
    }

    @Override
    public boolean alwaysOn() {
        return true;
    }

    @Override
    public void tick() {
        rateLimiter.tick();
        ClientPacketListener networkHandler = Flint.getClient().getConnection();
        if (networkHandler != null && !rateLimiter.isRateLimited() && !commandQueue.isEmpty()) {
            // No need to increment here, since our packet listener will do that for us.
            networkHandler.sendCommand(commandQueue.pop());
        }
    }

    @Override
    public EventResult onSendPacket(Packet<?> packet) {
        if (packet instanceof ServerboundChatCommandPacket || packet instanceof ServerboundChatPacket) {
            rateLimiter.increment();
        }

        return EventResult.PASS;
    }

}
