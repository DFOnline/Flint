package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public abstract class CodeBlockCaller extends CodeBlockWithArguments {
    protected String data;

    protected CodeBlockCaller(String data) {
        this.data = data;
    }

    protected CodeBlockCaller(JsonObject json) {
        super(json);
        data = json.get("data").getAsString();
    }

    @Override
    public String toString() {
        return "data='" + data + "'";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("data", data);
        return super.toJSON(json);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
