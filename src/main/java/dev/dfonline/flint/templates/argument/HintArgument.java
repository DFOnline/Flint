package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class HintArgument extends Argument {
    public enum HintType {
        FUNCTION,
        UNKNOWN
    }
    private HintType type;
    public HintArgument(JsonObject json, JsonObject data) {
        super(json);
        type = switch (data.get("id").getAsString()) {
            case "function" -> HintType.FUNCTION;
            default -> HintType.UNKNOWN;
        };
    }

    @Override
    public String toString() {
        return "Hint [type=" + type + " " + super.toString() + "]";
    }
}
