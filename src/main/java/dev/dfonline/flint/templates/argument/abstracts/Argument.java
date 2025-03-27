package dev.dfonline.flint.templates.argument.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.JSONable;
import dev.dfonline.flint.templates.argument.*;

public abstract class Argument implements JSONable {
    protected int slot;

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
            case "pn_el" -> new ParameterArgument(json, data);
            case "pot" -> new PotionArgument(json, data);
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "slot=" + slot;
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("slot", slot);
        JsonObject item = new JsonObject();
        JsonObject data = getData();
        item.add("data", data);
        item.addProperty("id", getID());
        json.add("item", item);
        return json;
    }

    protected abstract JsonObject getData();
    public abstract String getID();
}
