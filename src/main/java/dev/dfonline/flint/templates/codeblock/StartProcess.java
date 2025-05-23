package dev.dfonline.flint.templates.codeblock;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.codeblock.abstracts.CodeBlockCaller;

public class StartProcess extends CodeBlockCaller {
    public StartProcess(JsonObject json) {
        super(json);
    }

    public StartProcess(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "StartProcess [" + super.toString() + "]";
    }

    @Override
    public String getBlock() {
        return "start_process";
    }
}
