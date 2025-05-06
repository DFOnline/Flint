package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockCaller;

public class CallFunction extends CodeBlockCaller {
    public CallFunction(JsonObject json) {
        super(json);
    }

    public CallFunction(String data) {
        super(data);
    }

    @Override
    public String toString() {
        return "CallFunction [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "call_func";
    }
}
