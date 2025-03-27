package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockSubAction;

public class SelectObject extends CodeBlockSubAction {
    public SelectObject(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "SelectObject [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "select_obj";
    }
}
