package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class NumberArgument extends Argument {
    private double number;
    public NumberArgument(JsonObject json, JsonObject data) {
        super(json);
        this.number = data.get("name").getAsDouble();
    }

    @Override
    public String toString() {
        return "Number [value=" + number + " " + super.toString() + "]";
    }
}
