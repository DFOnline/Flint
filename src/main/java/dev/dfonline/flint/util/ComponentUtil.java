package dev.dfonline.flint.util;

import dev.dfonline.flint.Flint;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public final class ComponentUtil {

    private ComponentUtil() {
    }

    public static void textToString(Text content, StringBuilder build, ColorMode colorMode) {
        TextColor lastColor = null;
        for (Text text : content.getSiblings()) {
            if (colorMode != ColorMode.MINI_MESSAGE) {
                TextColor color = text.getStyle().getColor();
                if (color != null && lastColor != color && colorMode != ColorMode.NONE) {
                    lastColor = color;
                    if (color.getName().contains("#")) {
                        build.append(String.join(colorMode.getPrefix(), color.getName().split("")).replace("#", colorMode.getPrefix() + "x").toLowerCase());
                    } else {
                        build.append(Formatting.valueOf(String.valueOf(color).toUpperCase()).toString().replace("§", colorMode.getPrefix()));
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

    public enum ColorMode {
        SECTION("§"),
        AMPERSAND("&"),
        MINI_MESSAGE(null),
        NONE(null);

        private final String prefix;

        ColorMode(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }
    }

}
