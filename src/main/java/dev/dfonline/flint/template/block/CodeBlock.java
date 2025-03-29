package dev.dfonline.flint.template.block;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import dev.dfonline.flint.template.BlockTypeIdResolver;
import dev.dfonline.flint.template.value.impl.GenericBlock;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "block",
        visible = true,
        defaultImpl = GenericBlock.class
)
@JsonTypeIdResolver(BlockTypeIdResolver.class)
public abstract class CodeBlock {

    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CodeBlock{" +
                "id='" + this.id + '\'' +
                '}';
    }

}
