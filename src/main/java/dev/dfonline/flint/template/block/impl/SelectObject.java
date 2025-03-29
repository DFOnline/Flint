package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.Attribute;
import dev.dfonline.flint.template.block.BaseBlock;

public class SelectObject extends BaseBlock {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subAction;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    public SelectObject() {
        super("select_obj");
    }

    public SelectObject(String action) {
        this();
        this.setAction(action);
    }

    public SelectObject(String action, String subAction) {
        this(action);
        this.subAction = subAction;
    }

    public SelectObject(String action, Attribute attribute) {
        this(action);
        if (attribute != null) {
            this.attribute = attribute.getValue();
        } else {
            this.attribute = null;
        }
    }

    public SelectObject(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, String subAction, Attribute attribute) {
        this(action, subAction);
        if (attribute != null) {
            this.attribute = attribute.getValue();
        } else {
            this.attribute = null;
        }
    }

    public SelectObject(String action, String subAction, ArgumentContainer args) {
        this(action, subAction);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, Attribute attribute, ArgumentContainer args) {
        this(action, attribute);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, String subAction, Attribute attribute,
                        ArgumentContainer args) {
        this(action, subAction, attribute);
        this.getArguments().setItems(args.getItems());
    }

    public String getSubAction() {
        return this.subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
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
