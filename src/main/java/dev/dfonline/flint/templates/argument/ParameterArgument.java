package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import org.jetbrains.annotations.Nullable;

public class ParameterArgument extends Argument {
    public enum ParameterType {
        ANY_VALUE("any"),
        VARIABLE("var"),
        LIST("list"),
        DICTIONARY("dict"),

        NUMBER("num"),
        TEXT("comp"),
        STRING("txt"),
        SOUND("snd"),
        VECTOR("vec"),
        LOCATION("loc"),
        PARTICLE("part"),
        POTION("pot"),
        ITEM("item"),


        ;
        public final String name;
        ParameterType(String name) {
            this.name = name;
        }
    }

    private ParameterType fromName(String name) {
        for (ParameterType type : ParameterType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }

    private String name;
    private ParameterType type;
    private boolean optional;
    private boolean plural;
    private Argument default_value;

    public ParameterArgument(JsonObject json, JsonObject data) {
        super(json);
        name = data.get("name").getAsString();
        type = fromName(data.get("type").getAsString());
        optional = data.get("optional").getAsBoolean();
        plural = data.get("plural").getAsBoolean();
        if (data.has("default_value")) {
            default_value = Argument.fromJson(data.get("default_value").getAsJsonObject());
        }
    }

    public ParameterArgument(int slot, String name, ParameterType type, boolean optional, boolean plural, @Nullable Argument default_value) {
        super(slot);
        this.name = name;
        this.type = type;
        this.optional = optional;
        this.plural = plural;
        this.default_value = default_value;
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("type", type.name);
        data.addProperty("optional", optional);
        data.addProperty("plural", plural);
        if (default_value != null) {
            data.add("default_value", default_value.toJSON());
        }
        return data;
    }

    @Override
    public String getID() {
        return "pn_el";
    }

    @Override
    public String toString() {
        return "Paramter [name=" + name + ", type=" + type + ", optional=" + optional + ", plural=" + plural + ", default_value=" + default_value + " " + super.toString() + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }

    public Argument getDefault_value() {
        return default_value;
    }

    public void setDefault_value(Argument default_value) {
        this.default_value = default_value;
    }
}
