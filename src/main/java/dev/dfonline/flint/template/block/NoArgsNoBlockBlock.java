package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dfonline.flint.template.ArgumentContainer;

public abstract class NoArgsNoBlockBlock extends BaseBlock {

    public NoArgsNoBlockBlock(String block) {
        super(block);
    }

    @Override
    @JsonIgnore
    public ArgumentContainer getArguments() {
        return super.getArguments();
    }

    @Override
    @JsonProperty("block")
    public String getBlock() {
        return super.getBlock();
    }
}
