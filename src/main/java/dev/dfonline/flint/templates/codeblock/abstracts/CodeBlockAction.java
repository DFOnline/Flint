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

    protected CodeBlockAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "action='" + action + "' ";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("action", action);
        return super.toJSON(json);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}