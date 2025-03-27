package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.CodeBlock;

public class Else extends CodeBlock {
    public Else() {

    }

    @Override
    public String toString() {
        return "Else";
    }

    @Override
    public String getBlock() {
        return "else";
    }

    @Override
    public JsonObject toJSON() {
        return super.toJSON(new JsonObject());
    }
}
