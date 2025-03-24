package dev.dfonline.flint.util.message.impl.prefix;

import dev.dfonline.flint.util.PaletteColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.TextDecoration;

public class DebugMessage extends PrefixedMessage {

    private static final Component DEBUG_PREFIX = Component.text("»", PaletteColor.PINK_LIGHT, TextDecoration.BOLD);

    public DebugMessage(Component component) {
        super(DEBUG_PREFIX, component);
    }

    public DebugMessage(Component component, boolean actionbar) {
        super(DEBUG_PREFIX, component, actionbar);
    }

    public DebugMessage(String key, ComponentLike... args) {
        super(DEBUG_PREFIX, Component.translatable(key, args));
    }

    public DebugMessage(String key, boolean actionbar, ComponentLike... args) {
        super(DEBUG_PREFIX, Component.translatable(key, args), actionbar);
    }

    public DebugMessage(String message) {
        super(DEBUG_PREFIX, Component.text(message));
    }

    public DebugMessage(String message, boolean actionbar) {
        super(DEBUG_PREFIX, Component.text(message), actionbar);
    }

}
