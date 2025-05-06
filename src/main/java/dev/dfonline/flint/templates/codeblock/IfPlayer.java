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

    public IfPlayer(String action, PlayerTarget target, boolean not) {
        super(action, not);
        this.target = target;
    }

    @Override
    public String toString() {
        return "IfPlayer [target=" + target + " " + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "if_player";
    }

    public PlayerTarget getTarget() {
        return target;
    }

    public void setTarget(PlayerTarget target) {
        this.target = target;
    }
}
