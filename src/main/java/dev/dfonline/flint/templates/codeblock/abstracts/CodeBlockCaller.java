package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;

public class CodeBlockCaller extends CodeBlockWithArguments {
    protected String data;

    protected CodeBlockCaller(JsonObject json) {
        super(json);
        data = json.get("data").getAsString();
    }

    @Override
    public String toString() {
        return "data='" + data + "'";
    }
}
