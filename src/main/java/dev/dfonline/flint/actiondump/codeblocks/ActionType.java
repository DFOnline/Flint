package dev.dfonline.flint.actiondump.codeblocks;

import dev.dfonline.flint.actiondump.data.Icon;

public record ActionType(
        String name,
        String codeblockName,
        Icon icon,
        BlockTagType[] tags,
        String[] aliases
) {
}
