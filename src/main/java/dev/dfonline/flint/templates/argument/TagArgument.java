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
}
