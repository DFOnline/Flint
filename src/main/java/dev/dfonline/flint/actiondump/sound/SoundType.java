package dev.dfonline.flint.actiondump.sound;

import dev.dfonline.flint.actiondump.data.Icon;

public record SoundType(
        String sound,
        String soundId,
        Icon icon,
        SoundVariant[] variants
) {
}
