package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class StringValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    public StringValue() {
        this.setId("txt");
    }

    public StringValue(String text) {
        this();
        this.data.put("name", text);
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
    public String getText() {
        return this.data.get("name");
    }

    @JsonIgnore
    public void setText(String text) {
        this.data.put("name", text);
    }

}
