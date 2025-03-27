package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.CodeBlock;

public abstract class CodeBlockEvent extends CodeBlock {
    protected String action = null;
    protected CodeBlockEvent(JsonObject json) {
        if (json.has("action")) {
            this.action = json.get("action").getAsString();
        }
    }

    @Override
    public String toString() {
        return "action=" + action;
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("action", action);
        return super.toJSON(json);
    }
}
