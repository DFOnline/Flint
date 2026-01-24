package dev.dfonline.flint.actiondump;

import com.google.gson.*;
import dev.dfonline.flint.actiondump.codeblocks.ActionType;
import dev.dfonline.flint.actiondump.codeblocks.CodeBlockType;
import dev.dfonline.flint.actiondump.gamevalues.GameValueCategory;
import dev.dfonline.flint.actiondump.gamevalues.GameValueType;
import dev.dfonline.flint.actiondump.particle.ParticleType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record ActionDump(
        CodeBlockType[] codeblocks,
        ActionType[] actions,
        GameValueCategory[] gameValueCategories,
        GameValueType[] gameValues,
        ParticleType[] particles
) {
    private static class Instance {
        private static ActionDump ACTION_DUMP;
    }

    public static ActionDump get() {
        if(Instance.ACTION_DUMP == null) {
            var gson = new GsonBuilder().create();
            try {
                var file = Files.readString(Path.of("./Flint/actiondump_minimessage.json"));
                Instance.ACTION_DUMP = gson.fromJson(file, ActionDump.class);
            } catch (IOException e) {
                throw new ActionDumpFileMissingException();
            }
        }
        return Instance.ACTION_DUMP;
    }

}
