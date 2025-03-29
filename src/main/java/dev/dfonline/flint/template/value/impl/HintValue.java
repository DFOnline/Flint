package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class HintValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    public HintValue() {
        this.setId("hint");
    }

    public HintValue(Type type) {
        this();
        this.data.put("id", type.id);
    }

    @JsonGetter("data")
    public Map<String, String> getData() {
        return this.data;
    }

    @JsonSetter("data")
    public void setData(Map<String, String> data) {
        this.data.clear();
        if (data != null) {
            this.data.putAll(data);
        }
    }

    @JsonIgnore
    public Type getHint() {
        return Type.fromId(this.data.get("id"));
    }

    @JsonIgnore
    public void setHint(Type type) {
        this.data.put("id", type.getId());
    }

    public enum Type {
        FUNCTION("function");

        private final String id;

        Type(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public static Type fromId(String strId) {
            for (Type type : Type.values()) {
                if (type.id.equals(strId)) {
                    return type;
                }
            }
            return null;
        }
    }

}

