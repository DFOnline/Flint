package dev.dfonline.flint.templates.argument.abstracts;

import com.google.gson.JsonObject;

public abstract class GenericStringArgument extends Argument {
    protected String value;
    public GenericStringArgument(JsonObject json, JsonObject data) {
        super(json);
        value = data.get("name").getAsString();
    }

    @Override
    public String toString() {
        return "value=" + value + " " + super.toString();
    }
}
