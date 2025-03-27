package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockIfStatement;
import dev.dfonline.flint.templates.codeblock.target.PlayerTarget;

public class IfPlayer extends CodeBlockIfStatement {
    private PlayerTarget target = PlayerTarget.NONE;

    public IfPlayer(JsonObject json) {
        super(json);
        if (json.has("target")) {
            target = PlayerTarget.fromString(json.get("target").getAsString());
        }
    }

    @Override
    public String toString() {
        return "IfPlayer [target=" + target + " " + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "if_player";
    }
}
