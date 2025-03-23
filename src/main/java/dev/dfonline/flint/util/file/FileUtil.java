package dev.dfonline.flint.util.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FileUtil {

    private FileUtil() {
    }

    public static void writeFile(Path path, String content) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.writeString(path, content, StandardOpenOption.WRITE);
    }

}
