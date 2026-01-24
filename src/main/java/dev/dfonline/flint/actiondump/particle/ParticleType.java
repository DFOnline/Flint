package dev.dfonline.flint.actiondump.particle;

import dev.dfonline.flint.actiondump.data.Icon;

public record ParticleType(
        String particle,
        Icon icon,
        String category,
        String[] fields
) {
}
