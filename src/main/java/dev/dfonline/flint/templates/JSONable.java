package dev.dfonline.flint.templates;

import com.google.gson.JsonObject;

public interface JSONable {
    JsonObject toJSON();
}
