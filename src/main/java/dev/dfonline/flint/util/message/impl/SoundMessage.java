package dev.dfonline.flint.util.message.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.FlintSound;
import dev.dfonline.flint.util.message.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public record SoundMessage(FlintSound sound) implements Message {

    @Override
    public void send() {
        Minecraft client = Flint.getClient();
        if (client == null || client.player == null || client.level == null) {
            return;
        }
        LocalPlayer player = Flint.getUser().getPlayer();
        client.level.playSound(player, player.getX(), player.getY(), player.getZ(), this.sound.getSoundEvent(), this.sound.getSource(), this.sound.getVolume(), this.sound.getPitch());
    }

}
