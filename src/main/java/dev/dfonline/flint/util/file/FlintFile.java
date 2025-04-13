package dev.dfonline.flint.util.file;

import java.nio.file.Path;

public enum FlintFile {

    ACTION_DUMP_SECTION(builder()
            .setName("actiondump_section.json")
            .build()),
    ACTION_DUMP_AMPERSAND(builder()
            .setName("actiondump_ampersand.json")
            .build()),
    ACTION_DUMP_MINI_MESSAGE(builder()
            .setName("actiondump_minimessage.json")
            .build()),
    ACTION_DUMP_PLAIN(builder()
            .setName("actiondump_plain.json")
            .build());

    private final Path path;

    FlintFile(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }

    public static ExternalFileBuilder builder() {
        return new ExternalFileBuilder();
    }

}
