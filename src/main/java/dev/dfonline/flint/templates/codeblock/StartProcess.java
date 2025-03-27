package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockCaller;

public class StartProcess extends CodeBlockCaller {
    public StartProcess(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return "StartProcess [" + super.toString() + "]";
    }
}
