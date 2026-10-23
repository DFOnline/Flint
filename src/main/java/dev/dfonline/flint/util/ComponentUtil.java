package dev.dfonline.flint.util;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.actiondump.ActionDumpFormat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;

public final class ComponentUtil {

    private ComponentUtil() {
    }

    public static void textToString(net.minecraft.network.chat.Component content, StringBuilder build, ActionDumpFormat format) {
        TextColor lastColor = null;
        for (net.minecraft.network.chat.Component text : content.getSiblings()) {
            if (format != ActionDumpFormat.MINI_MESSAGE) {
                TextColor color = text.getStyle().getColor();
                if (color != null && lastColor != color && format != ActionDumpFormat.NONE) {
                    lastColor = color;
                    if (color.serialize().contains("#")) {
                        build.append(String.join(format.getPrefix(), color.serialize().split("")).replace("#", format.getPrefix() + "x").toLowerCase());
                    } else {
                        build.append(ChatFormatting.valueOf(String.valueOf(color).toUpperCase()).toString().replace("§", format.getPrefix()));
                    }
                }
                build.append(text.getString());
            } else {
                build.append(serializeComponent(Flint.AUDIENCE.asAdventure(text)));
            }

        }
    }

    public static String serializeComponent(Component component) {
        StringBuilder build = new StringBuilder();

        if (component instanceof TextComponent t) {
            if (!t.content().equals(",")) {
                appendStyle(build, t.style());
            }
            build.append(t.content());
        }

        return build.toString();
    }

    private static void appendStyle(StringBuilder build, Style style) {
        style.decorations().forEach((decoration, state) -> {
            if (state == TextDecoration.State.TRUE) {
                build.append("<").append(decoration.toString().toLowerCase()).append(">");
            } else if (decoration == TextDecoration.ITALIC && state == TextDecoration.State.FALSE) {
                build.append("<!").append(decoration.toString().toLowerCase()).append(">");
            }
        });

        if (style.color() != null) {
            appendColor(build, style.color());
        }
    }

    private static void appendColor(StringBuilder build, net.kyori.adventure.text.format.TextColor color) {
        String colorStr = color.asHexString();
        NamedTextColor namedTextColor = NamedTextColor.namedColor(color.value());

        if (namedTextColor != null) {
            colorStr = namedTextColor.toString();
        }

        build.append("<").append(colorStr).append(">");
    }

}
