package dev.dfonline.flint.templates;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class CodeBlocks {
    private ArrayList<CodeBlock> blocks;
    private JsonObject blocksEncodedTemp;

    public static CodeBlocks fromJson(JsonObject blocks) {
        CodeBlocks codeBlocks = new CodeBlocks();
        codeBlocks.blocksEncodedTemp = blocks;

        return codeBlocks;
    }

    @Override
    public String toString() {
        return "CodeBlocks [blocks = " + blocks + ", blocksEncodedTemp = " + blocksEncodedTemp + "]";
    }
}
