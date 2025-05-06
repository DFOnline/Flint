package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public abstract class CodeBlockSubAction extends CodeBlockAction{
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

    protected CodeBlockSubAction(String action, String subAction, boolean not) {
        super(action);
        this.subAction = subAction;
        this.not = not;
    }

    @Override
    public String toString() {
        return "not=" + not + ", action=" + action + ", subAction=" + subAction;
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        if (not) {
            json.addProperty("attribute", "NOT");
        }
        if (subAction != null) {
            json.addProperty("subAction", subAction);
        }
        return json;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }
}
