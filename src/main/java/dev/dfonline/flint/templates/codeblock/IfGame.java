package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockIfStatement;

public class IfGame extends CodeBlockIfStatement {
    public IfGame(JsonObject json) {
        super(json);
    }

    public IfGame(String action, boolean not) {
        super(action, not);
    }

    @Override
    public String toString() {
        return "IfGame [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "if_game";
    }
}
