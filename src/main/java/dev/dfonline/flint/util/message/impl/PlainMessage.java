package dev.dfonline.flint.util.message.impl;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.message.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;

public class PlainMessage implements Message {

    private final String key;
    private final ComponentLike[] args;

    public PlainMessage(String key, ComponentLike... args) {
        this.key = key;
        this.args = args;
    }

    @Override
    public void send() {
        Flint.getUser().getPlayer().sendMessage(Component.translatable(this.key, this.args));
    }

}
