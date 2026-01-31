package dev.dfonline.flint.actiondump.gson;

import com.google.gson.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.lang.reflect.Type;

public class ComponentGson implements JsonSerializer<Component>, JsonDeserializer<Component> {
    public static MiniMessage MINI_MESSAGE = MiniMessage.builder().build();

    @Override
    public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return MINI_MESSAGE.deserialize("<gray>" + json.getAsString());
    }

    @Override
    public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(MINI_MESSAGE.serialize(src));
    }
}
