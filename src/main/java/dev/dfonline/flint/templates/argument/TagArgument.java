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

    public TagArgument(int slot, String option, String tag, String action, String block) {
        super(slot);
        this.option = option;
        this.tag = tag;
        this.action = action;
        this.block = block;
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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
