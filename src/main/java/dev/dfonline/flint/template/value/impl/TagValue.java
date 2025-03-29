package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class TagValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    public TagValue() {
        this.setId("bl_tag");
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
    public String getTag() {
        return this.data.get("tag");
    }

    @JsonIgnore
    public void setTag(String tag) {
        this.data.put("tag", tag);
    }

    @JsonIgnore
    public String getOption() {
        return this.data.get("option");
    }

    @JsonIgnore
    public void setOption(String option) {
        this.data.put("option", option);
    }

    @JsonIgnore
    public String getAction() {
        return this.data.get("action");
    }

    @JsonIgnore
    public void setAction(String action) {
        this.data.put("action", action);
    }

    @JsonIgnore
    public String getBlock() {
        return this.data.get("block");
    }

    @JsonIgnore
    public void setBlock(String block) {
        this.data.put("block", block);
    }

}
