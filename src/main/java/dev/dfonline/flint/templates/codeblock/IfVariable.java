package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.ParameterArgument;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockIfStatement;

public class IfVariable extends CodeBlockIfStatement {
    public IfVariable(JsonObject json) {
        super(json);
    }

    public IfVariable(String action, boolean not) {
        super(action, not);
    }

    @Override
    public String toString() {
        return "If Variable [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "if_var";
    }
}
