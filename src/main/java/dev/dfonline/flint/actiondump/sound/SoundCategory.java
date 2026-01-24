package dev.dfonline.flint.actiondump.sound;

import dev.dfonline.flint.actiondump.data.Icon;

public record SoundCategory(
        String identifier,
        Icon icon,
        boolean hasSubCategories
) {
}
