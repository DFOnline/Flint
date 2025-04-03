package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.dfonline.flint.template.ArgumentContainer;

public abstract class EventBlock extends BaseBlock {

    public static final String LS_CANCEL = "LS-CANCEL";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;

    protected EventBlock(String block, String action) {
        super(block);
        this.setAction(action);
    }

    public EventBlock(String block, String action, boolean lsCancel) {
        this(block, action);
        this.attribute = lsCancel ? LS_CANCEL : null;
    }

    public EventBlock(String block, String action, ArgumentContainer args) {
        this(block, action);
        this.getArguments().setItems(args.getItems());
    }

    public EventBlock(String block, String action, boolean lsCancel, ArgumentContainer args) {
        this(block, action, lsCancel);
        this.getArguments().setItems(args.getItems());
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
