package dev.dfonline.flint.actiondump.codeblocks;

import dev.dfonline.flint.actiondump.Icon;

public record BlockTagType(
        String name,
        Option[] options,
        String defaultOption,
        int slot
) {
    public record Option(
        String name,
        Icon icon,
        String[] aliases
    ) {

    }
}
