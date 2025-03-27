package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockIfStatement;

public class IfVariable extends CodeBlockIfStatement {
    public IfVariable(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "If Variable [" + super.toString() + "]";
    }
}
