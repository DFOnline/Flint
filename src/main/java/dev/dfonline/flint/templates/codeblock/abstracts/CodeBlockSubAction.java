package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public class CodeBlockSubAction extends CodeBlockAction{
    protected boolean not = false;
    protected String subAction = null;

    protected CodeBlockSubAction(JsonObject json) {
        super(json);

        if (json.has("attribute") && json.get("attribute").getAsString().equals("NOT")) {
            not = true;
        }
        if (json.has("subAction")) {
            subAction = json.get("subAction").getAsString();
        }
    }

    @Override
    public String toString() {
        return "not=" + not + ", action=" + action + ", subAction=" + subAction;
    }
}
