package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public abstract class CodeBlockIfStatement extends CodeBlockAction {
    protected boolean not = false;
    protected CodeBlockIfStatement(JsonObject json) {
        super(json);
        if (json.has("attribute") && json.get("attribute").getAsString().equals("NOT")) {
            not = true;
        }
    }

    @Override
    public String toString() {
        return "not=" + not + " " + super.toString();
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        if (not) {
            json.addProperty("attribute", "NOT");
        }
        return json;
    }
}
