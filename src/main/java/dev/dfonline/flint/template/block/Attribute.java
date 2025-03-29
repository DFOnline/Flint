package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Attribute {

    NOT("NOT"),
    LS_CANCEL("LS-CANCEL");

    private final String value;

    Attribute(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static Attribute fromValue(String value) {
        for (Attribute attr : values()) {
            if (attr.value.equals(value)) {
                return attr;
            }
        }
        return null;
    }

}
