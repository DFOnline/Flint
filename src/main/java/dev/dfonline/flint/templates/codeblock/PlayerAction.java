package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockAction;
import dev.dfonline.flint.templates.codeblock.target.PlayerTarget;

public class PlayerAction extends CodeBlockAction {
    private PlayerTarget target;
    public PlayerAction(JsonObject json) {
        super(json);
        if (json.has("target")) {
            target = target.fromString(json.get("target").getAsString());
        } else {
            target = PlayerTarget.NONE;
        }
    }

    public PlayerAction(String action, PlayerTarget target) {
        super(action);
        this.target = target;
    }

    @Override
    public String toString() {
        return "PlayerAction [target=" + target + " " + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "player_action";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = super.toJSON();
        if (target != PlayerTarget.NONE) {
            json.addProperty("target", target.name);
        }
        return json;
    }

    public PlayerTarget getTarget() {
        return target;
    }

    public void setTarget(PlayerTarget target) {
        this.target = target;
    }
}
