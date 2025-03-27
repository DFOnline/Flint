package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public abstract class CodeBlockAction extends CodeBlockWithArguments {
    protected String action = null;
    protected CodeBlockAction(JsonObject json) {
        super(json);
        if (json.has("action")) {
            action = json.get("action").getAsString();
        }
    }

    @Override
    public String toString() {
        return "action='" + action + "' ";
    }
}