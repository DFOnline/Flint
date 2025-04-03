package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

/**
 * {
 * "id": "block",
 * "block": "call_func",
 * "args": { // might be empty, always exists
 * "items": []
 * }
 * }
 */
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
