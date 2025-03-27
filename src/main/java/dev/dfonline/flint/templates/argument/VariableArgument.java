package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.VariableScope;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import net.minecraft.command.argument.ItemStackArgument;

public class VariableArgument extends Argument {
    private String name;
    private VariableScope scope;

    public VariableArgument(JsonObject json, JsonObject data) {
        super(json);
        scope = VariableScope.fromInternalName(data.get("scope").getAsString());
        name = data.get("name").getAsString();
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + ", scope=" + scope + " " + super.toString() + "]";
    }
}
