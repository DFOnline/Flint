package dev.dfonline.flint.util.message.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.FSound;
import dev.dfonline.flint.util.message.Message;

public record SoundMessage(FSound sound) implements Message {

    @Override
    public void send() {
        Flint.getUser().getPlayer().playSoundToPlayer(this.sound.getSoundEvent(), this.sound.getSource(), this.sound.getVolume(), this.sound.getPitch());
    }

}
