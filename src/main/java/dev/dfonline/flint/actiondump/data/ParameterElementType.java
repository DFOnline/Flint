package dev.dfonline.flint.actiondump.data;

import net.kyori.adventure.text.Component;

public record ParameterElementType(
        String type,
        boolean plural,
        boolean optional,
        Component[] description,
        Component[][] notes,
        Component text
) {
}
