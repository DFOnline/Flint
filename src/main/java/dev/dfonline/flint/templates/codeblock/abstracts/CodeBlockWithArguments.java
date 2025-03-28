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
    protected CodeBlockWithArguments() {
        arguments = new Arguments();
    }

    public Arguments getArguments() {
        return arguments;
    }

    public JsonObject toJSON(JsonObject current) {
        current.add("args", arguments.toJson());
        return super.toJSON(current);
    }

    public CodeBlock setArguments(Arguments arguments) {
        this.arguments = arguments;
        return this;
    }
}
