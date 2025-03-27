package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.GenericStringArgument;

public class StringArgument extends GenericStringArgument {
    public StringArgument(JsonObject json, JsonObject data) {
        super(json, data);
    }

    @Override
    public String toString() {
        return "String [" + super.toString() + "]";
    }

    @Override
    public String getID() {
        return "txt";
    }
}
