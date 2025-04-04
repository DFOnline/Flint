package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;

/**
 * {
 * id: "block",
 * block: "if_player",
 * args: { // might be empty, always exists
 * items: []
 * },
 * action: "<action name or empty string>",
 * attribute: "<either NOT or field is not set>",
 * target: "<some string or field is not set>",
 * }
 */
public class ConditionBlock extends BaseBlock {

    public static final String NOT = "NOT";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    public ConditionBlock(String block, String action) {
        super(block);
        this.setAction(action);
    }

    public ConditionBlock(String block, String action, boolean not) {
        this(block, action);
        this.attribute = not ? NOT : null;
    }

    public ConditionBlock(String block, String action, ArgumentContainer args) {
        this(block, action);
        this.getArguments().setItems(args.getItems());
    }

    public ConditionBlock(String block, String action, boolean not, ArgumentContainer args) {
        this(block, action, not);
        this.getArguments().setItems(args.getItems());
    }

    @JsonIgnore
    public boolean isNot() {
        return this.attribute != null && this.attribute.equals(NOT);
    }

    @JsonIgnore
    public void setNot(boolean not) {
        this.attribute = not ? NOT : null;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
