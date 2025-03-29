package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;
import dev.dfonline.flint.template.value.VariableScope;

import java.util.HashMap;
import java.util.Map;

public class VariableValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    private VariableScope scope = VariableScope.GAME;

    public VariableValue() {
        this.setId("var");
    }

    public VariableValue(String variableName, VariableScope scope) {
        this();
        this.data.put("name", variableName);
        this.scope = scope;
    }

    public VariableValue(String variableName) {
        this(variableName, VariableScope.LOCAL);
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
    public String getName() {
        return this.data.get("name");
    }

    @JsonIgnore
    public void setName(String value) {
        this.data.put("name", value);
    }

    @JsonIgnore
    public String getScopeId() {
        return this.scope.getId();
    }

    @JsonIgnore
    public void setScopeId(String id) {
        VariableScope varScope = VariableScope.fromId(id);
        if (varScope != null) {
            this.scope = varScope;
        }
    }

    @JsonIgnore
    public VariableScope getScope() {
        return this.scope;
    }

    @JsonIgnore
    public void setScope(VariableScope scope) {
        this.scope = scope;
    }

}
