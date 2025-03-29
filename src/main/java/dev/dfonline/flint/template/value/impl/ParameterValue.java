package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import dev.dfonline.flint.template.value.Value;

public class ParameterValue extends Value {

    private ParameterData data;

    public ParameterValue() {
        this.setId("pn_el");
    }

    public ParameterValue(Type type, String name, boolean plural, boolean optional, Value defaultValue) {
        this();
        this.data = new ParameterData(type, name, plural, optional, defaultValue);
    }

    public ParameterData getData() {
        return this.data;
    }

    public void setData(ParameterData data) {
        this.data = data;
    }

    public static class ParameterData {

        private String type;
        private String name;
        private boolean plural = false;
        private boolean optional = false;
        private Value defaultValue;

        public ParameterData() {
        }

        public ParameterData(Type type, String name) {
            this.type = type.getId();
            this.name = name;
        }

        public ParameterData(Type type, String name, boolean plural) {
            this.type = type.getId();
            this.name = name;
            this.plural = plural;
        }

        public ParameterData(Type type, String name, boolean optional, Value defaultValue) {
            this.type = type.getId();
            this.name = name;
            this.optional = optional;
            this.defaultValue = defaultValue;
        }

        public ParameterData(Type type, String name, boolean plural, boolean optional) {
            this.type = type.getId();
            this.name = name;
            this.plural = plural;
            this.optional = optional;
        }

        public ParameterData(Type type, String name, boolean plural, boolean optional, Value defaultValue) {
            this.type = type.getId();
            this.name = name;
            this.plural = plural;
            this.optional = optional;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPlural() {
            return this.plural;
        }

        public void setPlural(boolean plural) {
            this.plural = plural;
        }

        public boolean isOptional() {
            return this.optional;
        }

        public void setOptional(boolean optional) {
            this.optional = optional;
        }

        @JsonGetter("default_value")
        public Value getDefaultValue() {
            return this.defaultValue;
        }

        public void setDefaultValue(Value defaultValue) {
            this.defaultValue = defaultValue;
        }
    }

    public enum Type {
        STRING("txt"),
        TEXT("comp"),
        NUMBER("num"),
        LOCATION("loc"),
        VECTOR("vec"),
        SOUND("snd"),
        PARTICLE("part"),
        POTION("pot"),
        VARIABLE("var"),
        GAME_VALUE("g_val"),
        LIST("list"),
        DICTIONARY("dict"),
        ANY("any");

        private final String id;

        Type(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }

}
