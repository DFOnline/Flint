package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class TagArgument extends Argument {
    private String option, tag, action, block;

    public TagArgument(JsonObject json, JsonObject data) {
        super(json);
        option = data.get("option").getAsString();
        tag = data.get("tag").getAsString();
        action = data.get("action").getAsString();
        block = data.get("block").getAsString();
    }

    @Override
    public String toString() {
        return "Tag [option=" + option + ", tag=" + tag + ", action=" + action + ", block=" + block + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("option", option);
        data.addProperty("tag", tag);
        data.addProperty("action", action);
        data.addProperty("block", block);
        return data;
    }

    @Override
    public String getID() {
        return "bl_tag";
    }
}
