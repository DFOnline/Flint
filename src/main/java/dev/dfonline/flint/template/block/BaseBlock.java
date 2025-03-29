package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.value.Value;

public abstract class BaseBlock extends CodeBlock {

    private String block;
    private String action = "";
    private ArgumentContainer args = new ArgumentContainer();

    public BaseBlock(String block) {
        this.setId("block");
        this.block = block;
    }

    public String getBlock() {
        return this.block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @JsonGetter("args")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArgumentContainer getArguments() {
        return this.args;
    }

    @JsonSetter("args")
    public void setArguments(ArgumentContainer args) {
        this.args = args;
    }

    public void addArgument(int slot, Value value) {
        this.args.set(slot, value);
    }

    @Override
    public String toString() {
        return "BaseBlock{" +
                "block='" + this.block + '\'' +
                ", action='" + this.action + '\'' +
                ", args=" + this.args +
                '}';
    }

}

