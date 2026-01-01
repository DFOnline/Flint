package dev.dfonline.flint.util.message.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.FlintSound;
import dev.dfonline.flint.util.message.Message;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;

public record SoundMessage(FlintSound sound) implements Message {

    @Override
    public void send() {
        MinecraftClient client = Flint.getClient();
        if (client == null || client.player == null || client.world == null) {
            return;
        }
        ClientPlayerEntity player = Flint.getUser().getPlayer();
        client.world.playSound(player, player.getX(), player.getY(), player.getZ(), this.sound.getSoundEvent(), this.sound.getSource(), this.sound.getVolume(), this.sound.getPitch());
    }

}
