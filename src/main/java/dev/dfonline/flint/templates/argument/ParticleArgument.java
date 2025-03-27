package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class ParticleArgument extends Argument {
    private JsonObject data;
    public ParticleArgument(JsonObject json, JsonObject data) {
        super(json);
        this.data = data.deepCopy();
    }

    @Override
    public String toString() {
        return "Particle [" + super.toString() + " data=" + data + "]";
    }
}
