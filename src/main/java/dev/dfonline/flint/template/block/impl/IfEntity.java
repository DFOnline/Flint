package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.ConditionBlock;

public class IfEntity extends ConditionBlock {

    private String target;

    @JsonGetter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTarget() {
        return this.target;
    }

    @JsonSetter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setTarget(String target) {
        this.target = target;
    }

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

    public IfEntity(String action, boolean not, String target, ArgumentContainer args) {
        super("if_entity", action, not, args);
        this.setTarget(target);
    }
}
