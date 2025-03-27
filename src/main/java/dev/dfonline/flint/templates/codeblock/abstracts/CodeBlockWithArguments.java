package dev.dfonline.flint.templates.codeblock.abstracts;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.Arguments;
import dev.dfonline.flint.templates.CodeBlock;

public abstract class CodeBlockWithArguments extends CodeBlock {
    protected Arguments arguments;
    protected CodeBlockWithArguments(JsonObject json) {
        //TODO: Parse Arguments
    }

    public void setArguments(Arguments args) {
        this.arguments = args;
    }

    public Arguments getArguments() {
        return arguments;
    }
}
