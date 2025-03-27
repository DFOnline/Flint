package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class SoundArgument extends Argument {
    private String sound;
    private double pitch, volume;
    private String variant = null;
    public SoundArgument(JsonObject json, JsonObject data) {
        super(json);
        pitch = data.get("pitch").getAsDouble();
        volume = data.get("vol").getAsDouble();
        sound = data.get("sound").getAsString();
        if (data.has("variant")) {
            variant = data.get("variant").getAsString();
        }
    }

    @Override
    public String toString() {
        return "Sound [sound=" + sound + ", pitch=" + pitch + ", volume=" + volume + ", variant" + variant + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("pitch", pitch);
        data.addProperty("vol", volume);
        data.addProperty("sound", sound);
        if (variant != null) {
            data.addProperty("variant", variant);
        }
        return data;
    }

    @Override
    public String getID() {
        return "snd";
    }
}
