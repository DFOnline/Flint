package dev.dfonline.flint.template;

import dev.dfonline.flint.template.block.CodeBlock;

import java.util.ArrayList;

public class CodeBuilder {
    private final ArrayList<CodeBlock> blocks = new ArrayList<>();
    public static CodeBuilder create() {
        return new CodeBuilder();
    }
    public CodeBuilder add(CodeBlock block) {
        blocks.add(block);
        return this;
    }
    public ArrayList<CodeBlock> build() {
        return blocks;
    }
}
