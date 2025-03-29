package dev.dfonline.flint.template.block.impl;

import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class StartProcess extends BaseBlock {

    private String data;

    public StartProcess() {
        super("start_process");
    }

    public StartProcess(String processName) {
        this();
        this.data = processName;
    }

    public StartProcess(String processName, ArgumentContainer args) {
        this(processName);
        this.getArguments().setItems(args.getItems());
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

}

