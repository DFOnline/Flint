package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockWithArguments;

public class Function extends CodeBlockWithArguments {
    private String functionName;

    public Function(JsonObject json) {
        super(json);
        functionName = json.get("data").getAsString();
    }

    public Function(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return "Function [functionName=" + functionName + "]";
    }

    @Override
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("data", functionName);
        return super.toJSON(json);
    }

    @Override
    public String getBlock() {
        return "func";
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
