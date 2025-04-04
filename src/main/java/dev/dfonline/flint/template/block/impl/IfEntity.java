package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.ConditionBlock;

public class IfEntity extends ConditionBlock {

    public IfEntity(String action) {
        super("if_entity", action);
    }

    public IfEntity(String action, boolean not) {
        super("if_entity", action, not);
    }

    public IfEntity(String action, ArgumentContainer args) {
        super("if_entity", action, args);
    }

    public IfEntity(String action, boolean not, ArgumentContainer args) {
        super("if_entity", action, not, args);
    }

}
