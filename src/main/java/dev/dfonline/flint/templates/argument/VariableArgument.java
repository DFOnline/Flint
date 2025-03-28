package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.VariableScope;
import dev.dfonline.flint.templates.argument.abstracts.Argument;

public class VariableArgument extends Argument {
    private String name;
    private VariableScope scope;

    public VariableArgument(JsonObject json, JsonObject data) {
        super(json);
        scope = VariableScope.fromInternalName(data.get("scope").getAsString());
        name = data.get("name").getAsString();
    }

    public VariableArgument(int slot, String name, VariableScope scope) {
        super(slot);
        this.name = name;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + ", scope=" + scope + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("scope", scope.internalName);
        return data;
    }

    @Override
    public String getID() {
        return "var";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableScope getScope() {
        return scope;
    }

    public void setScope(VariableScope scope) {
        this.scope = scope;
    }
}
