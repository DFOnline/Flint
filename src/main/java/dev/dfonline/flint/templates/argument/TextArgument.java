package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.GenericStringArgument;

public class TextArgument extends GenericStringArgument {
    public TextArgument(JsonObject json, JsonObject data) {
        super(json, data);
    }

    @Override
    public String toString() {
        return "Text [" + super.toString() + "]";
    }
}
