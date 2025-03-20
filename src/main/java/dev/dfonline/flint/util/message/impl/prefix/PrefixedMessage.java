package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.message.Message;
import net.kyori.adventure.text.Component;

public class PrefixedMessage implements Message {

    private final Component prefix;
    private final Component message;

    public PrefixedMessage(Component prefix, Component message) {
        this.prefix = prefix;
        this.message = message;
    }

    @Override
    public void send() {
        Flint.getUser().getPlayer().sendMessage(
                Component.empty()
                        .append(prefix)
                        .appendSpace()
                        .append(message)
        );
    }

}
