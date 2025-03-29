package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.Attribute;
import dev.dfonline.flint.template.block.BaseBlock;

public class IfPlayer extends BaseBlock {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    public IfPlayer() {
        super("if_player");
    }

    public IfPlayer(String action) {
        this();
        this.setAction(action);
    }

    public IfPlayer(String action, Attribute attribute) {
        this(action);
        if (attribute != null) {
            this.attribute = attribute.getValue();
        } else {
            this.attribute = null;
        }
    }

    public IfPlayer(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

    public IfPlayer(String action, Attribute attribute, ArgumentContainer args) {
        this(action, attribute);
        this.getArguments().setItems(args.getItems());
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        if (attribute != null) {
            this.attribute = attribute.getValue();
        } else {
            this.attribute = null;
        }
    }

}
