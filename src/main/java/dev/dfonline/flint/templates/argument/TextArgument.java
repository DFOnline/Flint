package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.GenericStringArgument;

public class TextArgument extends GenericStringArgument {
    public TextArgument(JsonObject json, JsonObject data) {
        super(json, data);
    }

    public TextArgument(int slot, String value) {
        super(slot, value);
    }

    @Override
    public String toString() {
        return "Text [" + super.toString() + "]";
    }

    @Override
    public String getID() {
        return "comp";
    }
}
