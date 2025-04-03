package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class IfPlayer extends BaseBlock {

    public static final String NOT = "NOT";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    public IfPlayer() {
        super("if_player");
    }

    public IfPlayer(String action) {
        this();
        this.setAction(action);
    }

    public IfPlayer(String action, boolean not) {
        this(action);
        this.attribute = not ? NOT : null;
    }

    public IfPlayer(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

    public IfPlayer(String action, boolean not, ArgumentContainer args) {
        this(action, not);
        this.getArguments().setItems(args.getItems());
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
