package dev.dfonline.flint.templates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.dfonline.flint.templates.Template.print;

public class Arguments {
    private final Set<Argument> arguments = new HashSet<>();
    public static Arguments fromJson(JsonArray args) {
        Arguments arguments = new Arguments();
        for (JsonElement arg : args) {
            JsonObject argObj = arg.getAsJsonObject();
            arguments.arguments.add(Argument.fromJson(argObj));
        }
        return arguments;
    }

    public List<Argument> getOrderedList() {
        return
            arguments
                .stream()
                .sorted((arg1, arg2) -> {
                    if (arg1.getSlot() == arg2.getSlot()) {
                        return 0;
                    }
                    return arg1.getSlot() - arg2.getSlot();
                })
                .toList();
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

        for (Argument argument : getOrderedList()) {
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

    public void add(Argument argument) {
        arguments.add(argument);
    }
}
