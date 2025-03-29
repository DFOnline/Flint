package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class NumberValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    public NumberValue() {
        this.setId("num");
    }

    public NumberValue(int value) {
        this();
        this.data.put("name", String.valueOf(value));
    }

    public NumberValue(double value) {
        this();
        this.data.put("name", String.valueOf(value));
    }

    public NumberValue(String value) {
        this();
        this.data.put("name", value);
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

    public String getValue() {
        return this.data.get("name");
    }

    @JsonIgnore
    public void setValue(String value) {
        this.data.put("name", value);
    }

}
