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

    public EntityAction(EntityTarget target, String action) {
        super(action);
        this.target = target;
    }

    @Override
    public String toString() {
        return "EntityAction [target=" + target + " " + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "entity_action";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        if (target != EntityTarget.NONE) {
            json.addProperty("target", target.name);
        }
        return json;
    }

    public EntityTarget getTarget() {
        return target;
    }

    public void setTarget(EntityTarget target) {
        this.target = target;
    }
}
