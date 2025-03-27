package dev.dfonline.flint.templates;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.Argument;

import java.util.ArrayList;

import static dev.dfonline.flint.templates.Template.print;

public class Arguments {
    private ArrayList<Argument> arguments;
    public static Arguments fromJson(JsonObject args) {
        return new Arguments();
    }

    public void printToChat() {
        print(" Arguments: ");
        if (arguments == null) {
            print("  - null");
            return;
        }
        for (Argument argument : arguments) {
            print("  -" + argument);
        }
    }

    @Override
    public String toString() {
        return "Arguments []";
    }
}
