package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockEvent;

public class EntityEvent extends CodeBlockEvent {
    public EntityEvent(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "EntityEvent [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "entity_event";
    }
}
