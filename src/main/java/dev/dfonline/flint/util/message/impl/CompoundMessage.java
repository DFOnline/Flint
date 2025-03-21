package dev.dfonline.flint.util.message.impl;

import dev.dfonline.flint.util.message.Message;

public record CompoundMessage(Message... components) implements Message {

    @Override
    public void send() {
        for (Message component : this.components) {
            component.send();
        }
    }

}
