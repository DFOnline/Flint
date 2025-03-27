package dev.dfonline.flint.templates.argument.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.*;

public abstract class Argument {
    private int slot;

    protected Argument(JsonObject json) {
        this.slot = json.get("slot").getAsInt();
    }

    public static Argument fromJson(JsonObject json) {
        JsonObject item = json.get("item").getAsJsonObject();
        JsonObject data = item.get("data").getAsJsonObject();
        return switch (item.get("id").getAsString()) {
            case "txt" -> new StringArgument(json, data);
            case "comp" -> new TextArgument(json, data);
            case "num" -> new NumberArgument(json, data);
            case "loc" -> new LocationArgument(json, data);
            case "vec" -> new VectorArgument(json, data);
            case "snd" -> new SoundArgument(json, data);
            case "part" -> new ParticleArgument(json, data);
            case "g_val" -> new GameValueArgument(json, data);
            case "var" -> new VariableArgument(json, data);
            case "hint" -> new HintArgument(json, data);
            case "item" -> new ItemArgument(json, data);
            case "bl_tag" -> new TagArgument(json, data);
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "slot=" + slot;
    }
}
