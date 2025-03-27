package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockEvent;

public class PlayerEvent extends CodeBlockEvent {
    public PlayerEvent(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "PlayerEvent [" + super.toString() + "]";
    }
}
