package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

/**
 * {
 * "id": "block",
 * "block": "game_action",
 * "args": { // might be empty, always exists
 * "items": []
 * }
 * }
 */
public class GameAction extends BaseBlock {

    public GameAction() {
        super("game_action");
    }

    public GameAction(String action) {
        this();
        this.setAction(action);
    }

    public GameAction(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
