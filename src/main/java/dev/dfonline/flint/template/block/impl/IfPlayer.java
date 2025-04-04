package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.ConditionBlock;

public class IfPlayer extends ConditionBlock {

    public IfPlayer(String action) {
        super("if_player", action);
    }

    public IfPlayer(String action, boolean not) {
        super("if_player", action, not);
    }

    public IfPlayer(String action, ArgumentContainer args) {
        super("if_player", action, args);
    }

    public IfPlayer(String action, boolean not, ArgumentContainer args) {
        super("if_player", action, not, args);
    }

}
