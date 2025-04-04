package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

/**
 * {
 * "id": "block",
 * "block": "set_var",
 * "args": { // might be empty, always exists
 * "items": []
 * }
 * }
 */
public class SetVariable extends BaseBlock {

    public SetVariable() {
        super("set_var");
    }

    public SetVariable(String action) {
        this();
        this.setAction(action);
    }

    public SetVariable(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
