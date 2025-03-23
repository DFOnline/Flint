package dev.dfonline.flint.util.file;

import java.nio.file.Path;

public enum ExternalFile {

    ;

    private final Path path;

    ExternalFile(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }

    public static ExternalFileBuilder builder() {
        return new ExternalFileBuilder();
    }

}
