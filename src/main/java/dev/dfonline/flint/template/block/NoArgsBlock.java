package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dfonline.flint.template.ArgumentContainer;

public abstract class NoArgsBlock extends BaseBlock {

    public NoArgsBlock(String block) {
        super(block);
    }

    @Override
    @JsonIgnore
    public ArgumentContainer getArguments() {
        return super.getArguments();
    }

}
