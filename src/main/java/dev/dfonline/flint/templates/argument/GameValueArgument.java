package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class GameValueArgument extends Argument {
    public enum GameValueTarget {
        NO_TARGET("Default"),
        DEFAULT("Default"),
        SELECTION("Selection"),
        KILLER("Killer"),
        DAMAGER("Damage"),
        VICTIM("Victim"),
        SHOOTER("Shooter"),
        PROJECTILE("Projectile"),
        LAST_ENTITY("LastEntity"),

        ;
        public final String name;
        GameValueTarget(String name) {
            this.name = name;
        }

        public static GameValueTarget fromString(String name) {
            for (GameValueTarget target : GameValueTarget.values()) {
                if (target.name.equals(name)) {
                    return target;
                }
            }
            return DEFAULT;
        }
    }

    private GameValueTarget target;
    private String type;
    public GameValueArgument(JsonObject json, JsonObject data) {
        super(json);
        target = GameValueTarget.fromString(data.get("target").getAsString());
        type = data.get("type").getAsString();
    }

    public GameValueArgument(int slot, String type, GameValueTarget target) {
        super(slot);
        this.target = target;
        this.type = type;
    }

    public GameValueTarget getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GameValue [target=" + target + ", type=" + type + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("target", target.name);
        data.addProperty("type", type);
        return data;
    }

    @Override
    public String getID() {
        return "g_val";
    }
}
