package dev.dfonline.flint.actiondump.data;

public record ParameterElementType(
        String type,
        boolean plural,
        boolean optional,
        String[] description,
        String[][] notes,
        String text
) {
}
