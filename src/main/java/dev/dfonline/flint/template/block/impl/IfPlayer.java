package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.ConditionBlock;

public class IfPlayer extends ConditionBlock {

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

    public IfPlayer(String action, String target, boolean not, ArgumentContainer args) {
        super("if_player", action, not, args);
        this.setTarget(target);
    }

}
