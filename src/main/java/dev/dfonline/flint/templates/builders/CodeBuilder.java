package dev.dfonline.flint.templates.builders;

import dev.dfonline.flint.templates.CodeBlock;
import dev.dfonline.flint.templates.CodeBlocks;

public class CodeBuilder {
    private CodeBlocks blocks = new CodeBlocks();
    public static CodeBuilder create() {
        return new CodeBuilder();
    }
    public CodeBuilder add(CodeBlock block) {
        blocks.add(block);
        return this;
    }
    public CodeBlocks build() {
        return blocks;
    }
}
