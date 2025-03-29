package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class Control extends BaseBlock {

    public Control() {
        super("control");
    }

    public Control(String action) {
        this();
        this.setAction(action);
    }

    public Control(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
