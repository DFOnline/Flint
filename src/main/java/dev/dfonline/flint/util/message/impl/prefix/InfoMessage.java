package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.util.PaletteColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextDecoration;

public class InfoMessage extends PrefixedMessage {

    private static final Component INFO_PREFIX = Component.text("»", PaletteColor.SKY_LIGHT, TextDecoration.ITALIC);

    public InfoMessage(String key, ComponentLike... args) {
        super(INFO_PREFIX, Component.translatable(key, args));
    }

}
