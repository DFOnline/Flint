package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockAction;
import dev.dfonline.flint.templates.codeblock.target.EntityTarget;

public class EntityAction extends CodeBlockAction {
    private EntityTarget target;
    public EntityAction(JsonObject json) {
        super(json);
        if (json.has("target")) {
            target = EntityTarget.fromString(json.get("target").getAsString());
        } else {
            target = EntityTarget.NONE;
        }
    }

    @Override
    public String toString() {
        return "EntityAction [target=" + target + " " + super.toString() + "]";
    }
}
