package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class HintArgument extends Argument {
    public enum HintType {
        FUNCTION("function"),
        UNKNOWN("");

        final String id;
        HintType(String id) {
            this.id = id;
        }
    }

    private HintType fromType(String type) {
        for (var t : HintType.values()) {
            if (t.id.equals(type)) {
                return t;
            }
        }
        return HintType.UNKNOWN;
    }

    private HintType type;
    public HintArgument(JsonObject json, JsonObject data) {
        super(json);
        type = fromType(data.get("id").getAsString());
    }

    @Override
    public String toString() {
        return "Hint [type=" + type + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("id", type.id);
        return data;
    }

    @Override
    public String getID() {
        return "hint";
    }
}
