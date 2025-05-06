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

    public LocationArgument(int slot, double x, double y, double z, double pitch, double yaw, boolean isBlock) {
        super(slot);
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.isBlock = isBlock;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch + ", yaw=" + yaw + ", isBlock=" + isBlock + " " + super.toString() +"]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("isBlock", isBlock);
        JsonObject loc = new JsonObject();
        loc.addProperty("x", x);
        loc.addProperty("y", y);
        loc.addProperty("z", z);
        loc.addProperty("pitch", pitch);
        loc.addProperty("yaw", yaw);
        data.add("loc", loc);
        return data;
    }

    @Override
    public String getID() {
        return "loc";
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }
}
