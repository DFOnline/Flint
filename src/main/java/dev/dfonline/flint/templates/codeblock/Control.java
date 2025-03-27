package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockAction;

public class Control extends CodeBlockAction {
    public Control(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "Control [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "control";
    }
}
