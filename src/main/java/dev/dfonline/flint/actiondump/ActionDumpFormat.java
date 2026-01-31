package dev.dfonline.flint.actiondump;

import dev.dfonline.flint.util.file.FlintFile;

public enum ActionDumpFormat {
    SECTION("§", FlintFile.ACTION_DUMP_SECTION),
    AMPERSAND("&", FlintFile.ACTION_DUMP_AMPERSAND),
    MINI_MESSAGE(null, FlintFile.ACTION_DUMP_MINI_MESSAGE),
    NONE(null, FlintFile.ACTION_DUMP_PLAIN);

    private final FlintFile file;
    private final String prefix;

    ActionDumpFormat(String prefix, FlintFile file) {
        this.prefix = prefix;
        this.file = file;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public FlintFile getFile() {
        return this.file;
    }

}
