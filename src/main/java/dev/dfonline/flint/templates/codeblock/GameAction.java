package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockAction;

public class GameAction extends CodeBlockAction {
    public GameAction(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "GameAction [" + super.toString() + "]";
    }
}
