package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class LocationArgument extends Argument {
    private boolean isBlock;
    private double x, y, z, pitch, yaw;
    public LocationArgument(JsonObject json, JsonObject data) {
        super(json);
        JsonObject loc = data.get("loc").getAsJsonObject();
        x = loc.get("x").getAsDouble();
        y = loc.get("y").getAsDouble();
        z = loc.get("z").getAsDouble();
        pitch = loc.get("pitch").getAsDouble();
        yaw = loc.get("yaw").getAsDouble();

        isBlock = data.get("isBlock").getAsBoolean();
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch + ", yaw=" + yaw + ", isBlock=" + isBlock + " " + super.toString() +"]";
    }
}
