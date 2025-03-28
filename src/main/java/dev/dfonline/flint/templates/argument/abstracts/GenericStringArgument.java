package dev.dfonline.flint.templates.argument.abstracts;

import com.google.gson.JsonObject;

public abstract class GenericStringArgument extends Argument {
    protected String value;
    protected GenericStringArgument(JsonObject json, JsonObject data) {
        super(json);
        value = data.get("name").getAsString();
    }
    protected GenericStringArgument(int slot, String value) {
        super(slot);
        this.value = value;
    }

    @Override
    public String toString() {
        return "value=" + value + " " + super.toString();
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("name", value);
        return data;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
