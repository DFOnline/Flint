package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.message.Message;
import net.kyori.adventure.text.Component;

public class PrefixedMessage implements Message {

    private final Component prefix;
    private final Component message;
    private final boolean actionbar;

    public PrefixedMessage(Component prefix, Component message) {
        this.prefix = prefix;
        this.message = message;
        this.actionbar = false;
    }

    public PrefixedMessage(Component prefix, Component message, boolean actionbar) {
        this.prefix = prefix;
        this.message = message;
        this.actionbar = actionbar;
    }

    @Override
    public void send() {
        Component component = Component.empty()
                .append(this.prefix)
                .appendSpace()
                .append(this.message);

        if (this.actionbar) {
            Flint.getUser().getPlayer().sendActionBar(component);
        } else {
            Flint.getUser().getPlayer().sendMessage(component);
        }
    }

}
