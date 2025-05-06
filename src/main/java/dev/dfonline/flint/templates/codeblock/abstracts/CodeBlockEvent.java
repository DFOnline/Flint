package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.CodeBlock;

public abstract class CodeBlockEvent extends CodeBlock {
    protected String action = null;
    protected boolean lagslayCancelled = false;
    protected CodeBlockEvent(JsonObject json) {
        if (json.has("action")) {
            this.action = json.get("action").getAsString();
        }
        if (json.has("attribute")) {
            // "LS-CANCEL"
            this.lagslayCancelled = true;
        }
    }
    protected CodeBlockEvent(String name, boolean lagslayCancelled) {
        this.action = name;
        this.lagslayCancelled = lagslayCancelled;
    }

    public String getName() {
        return action;
    }

    public void setName(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "action=" + action;
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("action", action);
        if (lagslayCancelled) {
            json.addProperty("attribute", "LS-CANCEL");
        }
        return super.toJSON(json);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isLagslayCancelled() {
        return lagslayCancelled;
    }

    public void setLagslayCancelled(boolean lagslayCancelled) {
        this.lagslayCancelled = lagslayCancelled;
    }
}
