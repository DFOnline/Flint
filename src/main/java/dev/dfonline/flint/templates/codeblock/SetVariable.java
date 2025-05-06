package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockAction;

public class SetVariable extends CodeBlockAction {
    public SetVariable(JsonObject json) {
        super(json);
    }

    public SetVariable(String action) {
        super(action);
    }

    @Override
    public String toString() {
        return "SetVariable [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "set_var";
    }
}
