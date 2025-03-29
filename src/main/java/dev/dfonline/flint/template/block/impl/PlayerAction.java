package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class PlayerAction extends BaseBlock {

    public PlayerAction() {
        super("player_action");
    }

    public PlayerAction(String action) {
        this();
        this.setAction(action);
    }

    public PlayerAction(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
