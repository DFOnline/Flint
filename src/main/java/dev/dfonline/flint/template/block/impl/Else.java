package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.BaseBlock;

// TODO: When turned into objects from JSON, we get back the wrong format (works when turning objects to json)
// Correct format:
/*
    {
        "id": "block",
        "block": "else"
    }
 */
// This block is unique as it does not have any "args".
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class Else extends BaseBlock {

    public Else() {
        super("else");
    }

    @Override
    @JsonGetter("args")
    public ArgumentContainer getArguments() {
        ArgumentContainer args = super.getArguments();
        if (args == null || args.getItems().isEmpty()) {
            return null;
        }
        return args;
    }

}
