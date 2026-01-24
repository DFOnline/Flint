package dev.dfonline.flint.actiondump;

public record BlockTagType(
        String name,
        Option[] options,
        String defaultOption,
        int slot
) {
    public record Option(
        String name,
        Icon icon,
        String[] aliases
    ) {

    }
}
