package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockSubAction;

public class Repeat extends CodeBlockSubAction {
    public Repeat(JsonObject json) {
        super(json);
    }

    public Repeat(String action, String subAction, boolean not) {
        super(action, subAction, not);
    }

    @Override
    public String toString() {
        return "Repeat [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "repeat";
    }
}
