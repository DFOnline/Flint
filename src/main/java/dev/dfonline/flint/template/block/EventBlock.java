package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;

public abstract class EventBlock extends BaseBlock {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    public EventBlock() {
        super(null);
        throw new UnsupportedOperationException("EventBlock is an abstract class and cannot be instantiated directly.");
    }

    public EventBlock(String action) {
        this();
        this.setAction(action);
    }

    public EventBlock(String action, Attribute attribute) {
        this(action);
        if (attribute != null) {
            this.attribute = attribute.getValue();
        } else {
            this.attribute = null;
        }
    }

    public EventBlock(String action, ArgumentContainer args) {
        this(action);
        this.getArguments().setItems(args.getItems());
    }

    public EventBlock(String action, Attribute attribute, ArgumentContainer args) {
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

