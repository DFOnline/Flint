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

    @Override
    public String toString() {
        return "PlayerAction [target=" + target + " " + super.toString() + "]";
    }
}
