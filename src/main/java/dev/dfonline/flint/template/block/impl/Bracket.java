package dev.dfonline.flint.template.block.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import dev.dfonline.flint.template.ArgumentContainer;
import dev.dfonline.flint.template.block.CodeBlock;

// TODO: When turned into objects from JSON, we get back the wrong format (works when turning objects to json)
// Correct format:
/*
    {
        "id": "bracket",
        "direct": "open",
        "type": "norm",
    }
 */
// This block is unique as its type is "bracket" and not "block", additionally not having any "args".
public class Bracket extends CodeBlock {

    @JsonProperty("args")
    private ArgumentContainer args = null;

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

    private String direct;
    private String type;

    public Bracket() {
//        super("bracket");
        setId("bracket");
    }

    public Bracket(Direction direct, Type type) {
        this();
        this.direct = direct.getValue();
        this.type = type.getValue();
    }

    public String getDirect() {
        return this.direct;
    }

    public void setDirect(Direction direct) {
        this.direct = direct.getValue();
    }

    @JsonProperty("direct")
    public void setDirectString(String direct) {
        this.direct = direct;
    }

    public String getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type.getValue();
    }

    @JsonProperty("type")
    public void setTypeString(String type) {
        this.type = type;
    }

    //    @Override
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    public String getBlock() {
//        return "bracket";
//    }
//
//    @Override
//    @JsonGetter("args")
//    public ArgumentContainer getArguments() {
//        return null;
//    }

}
