package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockIfStatement;
import dev.dfonline.flint.templates.codeblock.target.EntityTarget;

public class IfEntity extends CodeBlockIfStatement {
    private EntityTarget target;
    public IfEntity(JsonObject json) {
        super(json);
        if (json.has("target")) {
            target = EntityTarget.fromString(json.get("target").getAsString());
        } else {
            target = EntityTarget.NONE;
        }
    }

    @Override
    public String toString() {
        return "IfEntity [target=" + target + " " + super.toString() + "]";
    }
}
