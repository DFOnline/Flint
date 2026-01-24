package dev.dfonline.flint.actiondump;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public record ActionDump(
        CodeBlockType[] codeblocks,
        ActionType[] actions
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
