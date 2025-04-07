package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class ParticleArgument extends Argument {
    private JsonObject data;
    public ParticleArgument(JsonObject json, JsonObject data) {
        super(json);
        this.data = data.deepCopy();
    }

    public ParticleArgument(int slot, JsonObject data) {
        super(slot);
        this.data = data;
    }

    @Override
    public String toString() {
        return "Particle [" + super.toString() + " data=" + data + "]";
    }

    @Override
    protected JsonObject getData() {
        return data;
    }

    @Override
    public String getID() {
        return "part";
    }
    public JsonObject getValues() {
        return data;
    }
    public void setValues(JsonObject data) {
        this.data = data;
    }
}
