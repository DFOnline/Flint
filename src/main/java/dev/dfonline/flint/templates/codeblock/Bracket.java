package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.CodeBlock;

public class Bracket extends CodeBlock {
    public enum Direction {
        OPEN,
        CLOSE,
    }
    public enum Type {
        NORMAL,
        REPEAT
    }

    private Type type;
    private Direction direction;

    public Bracket(JsonObject json) {
        if (json.get("direct").getAsString().equals("open")) {
            direction = Direction.OPEN;
        } else {
            direction = Direction.CLOSE;
        }

        if (json.get("type").getAsString().equals("norm")) {
            type = Type.NORMAL;
        } else {
            type = Type.REPEAT;
        }
    }

    @Override
    public String toString() {
        return "Bracket[type=" + type + ", direction=" + direction + "]";
    }
}
