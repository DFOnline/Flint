package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

public class GenericBlock extends BaseBlock {

    public GenericBlock() {
        super("unknown");
    }

    public GenericBlock(String blockType) {
        super(blockType);
    }

    @JsonCreator
    public static GenericBlock create(
            @JsonProperty("block") String blockType,
            @JsonProperty("action") String action,
            @JsonProperty("args") ArgumentContainer args) {

        GenericBlock block = new GenericBlock(blockType);
        block.setAction(action);
        if (args != null) {
            block.setArguments(args);
        }
        return block;
    }

}
