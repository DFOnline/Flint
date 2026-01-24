package dev.dfonline.flint.actiondump;

import java.util.List;

public record ActionType(
        String name,
        String codeblockName,
        Icon icon,
        BlockTagType[] tags,
        String[] aliases
) {
}
