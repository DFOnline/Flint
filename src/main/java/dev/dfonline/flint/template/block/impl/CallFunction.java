package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

/**
 * {
 * "id": "block",
 * "block": "call_func",
 * "data": "<function name>",
 * "args": { // may be empty, always exists
 * "items": []
 * }
 * }
 */
public class CallFunction extends BaseBlock {

    private String data;

    public CallFunction() {
        super("call_func");
    }

    public CallFunction(String functionName) {
        this();
        this.data = functionName;
    }

    public CallFunction(String functionName, ArgumentContainer args) {
        this(functionName);
        this.getArguments().setItems(args.getItems());
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
