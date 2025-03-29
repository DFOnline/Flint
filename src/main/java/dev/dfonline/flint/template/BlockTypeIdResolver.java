package dev.dfonline.flint.template;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import dev.dfonline.flint.template.block.BaseBlock;
import dev.dfonline.flint.template.block.CodeBlock;
import dev.dfonline.flint.template.block.impl.Bracket;
import dev.dfonline.flint.template.block.impl.CallFunction;
import dev.dfonline.flint.template.block.impl.Else;
import dev.dfonline.flint.template.block.impl.Function;
import dev.dfonline.flint.template.block.impl.PlayerAction;
import dev.dfonline.flint.template.block.impl.SelectObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BlockTypeIdResolver extends TypeIdResolverBase {

    private final Map<String, Class<? extends CodeBlock>> typeMap;
    private JavaType baseType;

    public BlockTypeIdResolver() {
        this.typeMap = new HashMap<>();
        this.typeMap.put("select_obj", SelectObject.class);
        this.typeMap.put("call_function", CallFunction.class);
        this.typeMap.put("else", Else.class);
        this.typeMap.put("player_action", PlayerAction.class);
        this.typeMap.put("bracket", Bracket.class);
    }

    @Override
    public void init(JavaType type) {
        this.baseType = type;
    }

    @Override
    public String idFromValue(Object value) {
        if (value instanceof BaseBlock) {
            return ((BaseBlock) value).getBlock();
        } else if (value instanceof Bracket) {
            return "bracket";
        }
        return null;
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return this.idFromValue(value);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        Class<? extends CodeBlock> type = this.typeMap.get(id);
        if (type == null) {
            type = Function.class;
        }
        return context.getTypeFactory().constructSpecializedType(this.baseType, type);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

}
