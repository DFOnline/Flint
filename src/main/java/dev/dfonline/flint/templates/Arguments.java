package dev.dfonline.flint.templates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

import java.util.ArrayList;

import static dev.dfonline.flint.templates.Template.print;

public class Arguments {
    private final ArrayList<Argument> arguments = new ArrayList<>();
    public static Arguments fromJson(JsonArray args) {
        Arguments arguments = new Arguments();
        for (JsonElement arg : args) {
            JsonObject argObj = arg.getAsJsonObject();
            arguments.arguments.add(Argument.fromJson(argObj));
        }
        return arguments;
    }

    public void printToChat() {
        print(" Arguments: ");
        if (arguments.isEmpty()) {
            print("  - null");
            return;
        }
        for (Argument argument : arguments) {
            print("  -" + argument);
        }
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        JsonArray items = new JsonArray();

        for (Argument argument : arguments) {
            JsonObject argumentJson = argument.toJSON();
            items.add(argumentJson);
        }

        json.add("items", items);
        return json;
    }

    @Override
    public String toString() {
        return "Arguments []";
    }
}
