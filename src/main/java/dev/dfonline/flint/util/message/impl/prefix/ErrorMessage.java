package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.util.PaletteColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextDecoration;

public class ErrorMessage extends PrefixedMessage {

    private static final Component ERROR_PREFIX = Component.text("»", PaletteColor.RED_LIGHT, TextDecoration.BOLD);

    public ErrorMessage(String key, ComponentLike... args) {
        super(ERROR_PREFIX, Component.translatable(key, args));
    }

}