package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

/**
 * {
 * id: "block",
 * block: "repeat",
 * args: { // might be empty, always exists
 * items: []
 * },
 * action: "<action name or empty string>",
 * subAction: "<sub action name or empty string>",
 * attribute: "<either NOT or field is not set>"
 * }
 */
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
