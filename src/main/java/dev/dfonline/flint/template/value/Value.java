package dev.dfonline.flint.template.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.dfonline.flint.template.value.impl.*;

import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "id"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringValue.class, name = "txt"),
        @JsonSubTypes.Type(value = TextValue.class, name = "comp"),
        @JsonSubTypes.Type(value = NumberValue.class, name = "num"),
        @JsonSubTypes.Type(value = LocationValue.class, name = "loc"),
        @JsonSubTypes.Type(value = VectorValue.class, name = "vec"),
        @JsonSubTypes.Type(value = SoundValue.class, name = "snd"),
        @JsonSubTypes.Type(value = ParticleValue.class, name = "part"),
        @JsonSubTypes.Type(value = PotionValue.class, name = "pot"),
        @JsonSubTypes.Type(value = VariableValue.class, name = "var"),
        @JsonSubTypes.Type(value = GameValueValue.class, name = "g_val"),
        @JsonSubTypes.Type(value = ParameterValue.class, name = "pn_el"),
        @JsonSubTypes.Type(value = ItemValue.class, name = "item"),
        @JsonSubTypes.Type(value = TagValue.class, name = "bl_tag"),
        @JsonSubTypes.Type(value = HintValue.class, name = "hint"),
})
public abstract class Value {

    @JsonIgnore
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Value other)) {
            return false;
        }

        if (this.id == null) {
            return other.id == null;
        }

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Value{" +
                "id='" + this.id + '\'' +
                '}';
    }

}
