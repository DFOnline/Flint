package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.ConditionBlock;

public class IfGame extends ConditionBlock {

    public IfGame(String action) {
        super("if_game", action);
    }

    public IfGame(String action, boolean not) {
        super("if_game", action, not);
    }

    public IfGame(String action, ArgumentContainer args) {
        super("if_game", action, args);
    }

    public IfGame(String action, boolean not, ArgumentContainer args) {
        super("if_game", action, not, args);
    }

}
