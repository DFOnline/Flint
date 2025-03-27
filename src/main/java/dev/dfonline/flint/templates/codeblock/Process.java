package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockWithArguments;

public class Process extends CodeBlockWithArguments {
    private String processName;

    public Process(JsonObject json) {
        super(json);
        processName = json.get("data").getAsString();
    }

    @Override
    public String toString() {
        return "Process [processName=" + processName + "]";
    }

    @Override
    public String getBlock() {
        return "process";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("data", processName);
        return super.toJSON(json);
    }
}
