package dev.dfonline.flint.actiondump.gamevalues;

import dev.dfonline.flint.actiondump.data.Icon;

public record GameValueCategory(
        String identifier,
        int guiSlot,
        Icon icon
) {
}
