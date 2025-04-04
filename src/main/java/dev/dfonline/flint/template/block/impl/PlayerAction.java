package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

// Same as Control
public class PlayerAction extends BaseBlock {

    private String target;

    @JsonGetter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTarget() {
        return this.target;
    }

    @JsonSetter
    public void setTarget(String target) {
        this.target = target;
    }

    public PlayerAction() {
        super("player_action");
    }

    public PlayerAction(String action) {
        this();
        this.setAction(action);
    }

    public PlayerAction(String action, String target, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
        this.setTarget(target);
    }

    public PlayerAction(String action, String target) {
        this(action);
        this.setTarget(target);
    }

    public PlayerAction(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

}
