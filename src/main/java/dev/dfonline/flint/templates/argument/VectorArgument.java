package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class VectorArgument extends Argument {
    private double x, y, z;
    public VectorArgument(JsonObject json, JsonObject data) {
        super(json);
        x = data.get("x").getAsDouble();
        y = data.get("y").getAsDouble();
        z = data.get("z").getAsDouble();
    }

    @Override
    public String toString() {
        return "Vector [x=" + x + ", y=" + y + ", z=" + z + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("x", x);
        data.addProperty("y", y);
        data.addProperty("z", z);
        return data;
    }

    @Override
    public String getID() {
        return "vec";
    }
}
