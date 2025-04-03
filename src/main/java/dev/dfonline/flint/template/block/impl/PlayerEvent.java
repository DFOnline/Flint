package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.EventBlock;

// Same as Entity Event
public class PlayerEvent extends EventBlock {

    public PlayerEvent(String action) {
        super("event", action);
    }

    public PlayerEvent(String action, boolean lsCancel) {
        super("event", action, lsCancel);
    }

    public PlayerEvent(String action, ArgumentContainer args) {
        super("event", action, args);
    }

    public PlayerEvent(String action, boolean lsCancel, ArgumentContainer args) {
        super("event", action, lsCancel, args);
    }

}
