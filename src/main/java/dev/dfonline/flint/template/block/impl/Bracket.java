package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.dfonline.flint.template.block.ForceNoArgsNoBlockBlock;

/**
 * {
 * "id": "bracket",
 * "direct": "open",
 * "type": "norm"
 * }
 */
public class Bracket extends ForceNoArgsNoBlockBlock {

    public enum Direction {
        OPEN("open"),
        CLOSE("close");
        private final String value;

        Direction(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }
    }

    public enum Type {
        NORMAL("norm"),
        REPEAT("repeat");
        private final String value;

        Type(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }
    }

    @JsonProperty("direct")
    private String direct;

    @JsonProperty("type")
    private String type;

    public Bracket() {
        super("bracket");
    }

    public Bracket(Direction direct, Type type) {
        this();
        this.direct = direct.getValue();
        this.type = type.getValue();
    }

    @JsonProperty("direct")
    public String getDirect() {
        return this.direct;
    }

    @JsonProperty("direct")
    public void setDirect(String direct) {
        this.direct = direct;
    }

    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getBlock() {
        return null;
    }

    @Override
    public String toString() {
        return "Bracket{" +
                "id='" + getId() + '\'' +
                ", direct='" + this.direct + '\'' +
                ", type='" + this.type + '\'' +
                ", block='" + getBlock() + '\'' +
                '}';
    }
}
