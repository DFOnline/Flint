package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class Repeat extends BaseBlock {

    public Repeat() {
        super("repeat");
    }

    public Repeat(String action) {
        this();
        this.setAction(action);
    }

    public Repeat(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
