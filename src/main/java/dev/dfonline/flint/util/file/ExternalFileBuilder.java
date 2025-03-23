package dev.dfonline.flint.util.file;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.util.Logger;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.function.Consumer;

public class ExternalFileBuilder {

    private static final Logger LOGGER = Logger.of(ExternalFileBuilder.class);
    private String fileName;
    private boolean directory = false;

    public ExternalFileBuilder setName(String name) {
        this.fileName = name;
        return this;
    }

    public ExternalFileBuilder isDirectory(boolean isDirectory) {
        this.directory = isDirectory;
        return this;
    }

    private Path getMainDir() throws IOException {
        Path path = FabricLoader.getInstance().getGameDir().resolve(Flint.MOD_NAME);
        if (!Files.isDirectory(path)) {
            Files.createDirectory(path);
        }
        return path;
    }

    public Path buildRaw(@Nullable Consumer<Path> init) throws IOException {
        Path path = this.getMainDir().resolve(this.fileName);

        if (this.directory) {
            try {
                Files.createDirectory(path);
            } catch (FileAlreadyExistsException x) {
                if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                    throw x;
                }
            }
        } else {
            try {
                Files.createFile(path);
            } catch (FileAlreadyExistsException x) {
                if (!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
                    throw x;
                }
            }
        }
        if (init != null) {
            init.accept(path);
        }

        return path;
    }

    public Path buildRaw() throws IOException {
        return this.buildRaw(null);
    }

    public Path build(@Nullable Consumer<Path> init) {
        try {
            return this.buildRaw(init);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path build() {
        return this.build(null);
    }

    static Path nbt(String name) {
        return new ExternalFileBuilder()
                .isDirectory(false)
                .setName(name)
                .build(path -> {
                    try {
                        NbtIo.write(new NbtCompound(), path);
                    } catch (IOException e) {
                        LOGGER.error("Failed to write NBT file: " + path, e);
                    }
                });
    }

}
