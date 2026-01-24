package dev.dfonline.flint.actiondump.gamevalues;

import dev.dfonline.flint.actiondump.Icon;

public record GameValueType(
        Icon icon,
        String category,
        String[] aliases
) {
}
