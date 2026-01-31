package dev.dfonline.flint.actiondump.categories;

import dev.dfonline.flint.actiondump.data.Icon;

public record ValueCategory(
        String identifier,
        int guiSlot,
        Icon icon
) {
}
