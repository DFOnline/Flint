package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockSubAction;

public class Repeat extends CodeBlockSubAction {
    public Repeat(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "Repeat [" + super.toString() + "]";
    }
}
