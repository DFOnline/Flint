package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

// Same as Repeat
public class SelectObject extends BaseBlock {

    public static final String NOT = "NOT";
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

    public SelectObject(String action, boolean not) {
        this(action);
        this.attribute = not ? NOT : null;
    }

    public SelectObject(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, String subAction, boolean not) {
        this(action, subAction);
        this.attribute = not ? NOT : null;
    }

    public SelectObject(String action, String subAction, ArgumentContainer args) {
        this(action, subAction);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, boolean not, ArgumentContainer args) {
        this(action, not);
        this.getArguments().setItems(args.getItems());
    }

    public SelectObject(String action, String subAction, boolean not, ArgumentContainer args) {
        this(action, subAction, not);
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

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
