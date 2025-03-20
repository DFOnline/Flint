package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.util.PaletteColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextDecoration;

public class SuccessMessage extends PrefixedMessage {

    private static final Component SUCCESS_PREFIX = Component.text("»", PaletteColor.LIME_LIGHT_2, TextDecoration.BOLD);

    public SuccessMessage(String key, ComponentLike... args) {
        super(SUCCESS_PREFIX, Component.translatable(key, args));
    }

}
