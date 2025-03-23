package dev.dfonline.flint.util;

import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public final class ComponentUtil {

    private ComponentUtil()  {
    }

    public static void textToString(Text content, StringBuilder build, ColorMode colorMode) {
        TextColor lastColor = null;
        for (Text text : content.getSiblings()) {
            TextColor color = text.getStyle().getColor();
            if (color != null && (lastColor != color) && (colorMode != ColorMode.NONE)) {
                lastColor = color;
                if (color.getName().contains("#")) {
                    build.append(String.join(colorMode.prefix, color.getName().split("")).replace("#", colorMode.prefix + "x").toLowerCase());
                } else {
                    build.append(Formatting.valueOf(String.valueOf(color).toUpperCase()).toString().replace("§", colorMode.prefix));
                }
            }
            build.append(text.getString());
        }
    }

    public static String textToString(Text content) {
        var builder = new StringBuilder();
        textToString(content, builder, ColorMode.SECTION);
        return builder.toString();
    }

    public enum ColorMode {
        SECTION("§"),
        AMPERSAND("&"),
        NONE(null);

        private final String prefix;
        ColorMode(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

}
