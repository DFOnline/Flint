package dev.dfonline.flint.actiondump.codeblocks;

import dev.dfonline.flint.actiondump.data.Icon;

public record CodeBlockType(
        String name,
        String identifier,
        Icon item
) {
}
