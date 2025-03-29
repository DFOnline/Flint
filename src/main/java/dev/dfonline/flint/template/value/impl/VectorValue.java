package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.HashMap;
import java.util.Map;

public class VectorValue extends Value {

    private final Map<String, Double> data = new HashMap<>();

    public VectorValue() {
        this.setId("vec");
    }

    public VectorValue(double x, double y, double z) {
        this();
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    @JsonGetter("data")
    public Map<String, Double> getData() {
        return this.data;
    }

    @JsonSetter("data")
    public void setData(Map<String, Double> data) {
        this.data.clear();
        if (data != null) {
            this.data.putAll(data);
        }
    }

    @JsonIgnore
    public double getX() {
        return this.data.getOrDefault("x", 0.0);
    }

    @JsonIgnore
    public void setX(double x) {
        this.data.put("x", x);
    }

    @JsonIgnore
    public double getY() {
        return this.data.getOrDefault("y", 0.0);
    }

    @JsonIgnore
    public void setY(double y) {
        this.data.put("y", y);
    }

    @JsonIgnore
    public double getZ() {
        return this.data.getOrDefault("z", 0.0);
    }

    @JsonIgnore
    public void setZ(double z) {
        this.data.put("z", z);
    }

}
