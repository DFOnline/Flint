package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.Arguments;
import dev.dfonline.flint.templates.CodeBlock;

public abstract class CodeBlockWithArguments extends CodeBlock {
    protected Arguments arguments;
    protected CodeBlockWithArguments(JsonObject json) {
        JsonObject args = json.get("args").getAsJsonObject();
        arguments = Arguments.fromJson(json.getAsJsonObject("args").getAsJsonArray("items"));
    }

    public Arguments getArguments() {
        return arguments;
    }
}
