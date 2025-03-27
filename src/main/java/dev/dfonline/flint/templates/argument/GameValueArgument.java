package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class GameValueArgument extends Argument {
    private String target;
    private String type;
    public GameValueArgument(JsonObject json, JsonObject data) {
        super(json);
        target = data.get("target").getAsString();
        type = data.get("type").getAsString();
    }

    @Override
    public String toString() {
        return "GameValue [target=" + target + ", type=" + type + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("target", target);
        data.addProperty("type", type);
        return data;
    }

    @Override
    public String getID() {
        return "g_val";
    }
}
