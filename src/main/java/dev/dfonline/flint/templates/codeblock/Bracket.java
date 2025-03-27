package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.CodeBlock;

public class Bracket extends CodeBlock {
    @Override
    public String getID() {
        return "bracket";
    }

    @Override
    public String getBlock() {
        return null;
    }

    public enum Direction {
        OPEN("open"),
        CLOSE("close");

        public final String name;
        Direction(String name) {
            this.name = name;
        }
    }
    public enum Type {
        NORMAL("norm"),
        REPEAT("repeat");

        public final String name;
        Type(String name) {
            this.name = name;
        }
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
    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("type", type.name);
        json.addProperty("direct", direction.name);
        return super.toJSON(json);
    }

    @Override
    public String toString() {
        return "Bracket[type=" + type + ", direction=" + direction + "]";
    }
}
