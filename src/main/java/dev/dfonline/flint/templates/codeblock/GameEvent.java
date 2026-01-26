package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockEvent;

public class GameEvent extends CodeBlockEvent {
    public GameEvent(JsonObject json) {
        super(json);
    }

    public GameEvent(String name, boolean lagslayCancelled) {
        super(name, lagslayCancelled);
    }

    @Override
    public String toString() {
        return "GameEvent [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "game_event";
    }
}
