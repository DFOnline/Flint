package dev.dfonline.flint.actiondump;

import com.google.gson.*;
import dev.dfonline.flint.actiondump.codeblocks.ActionType;
import dev.dfonline.flint.actiondump.codeblocks.CodeBlockType;
import dev.dfonline.flint.actiondump.gamevalues.GameValueCategory;
import dev.dfonline.flint.actiondump.gamevalues.GameValueType;
import dev.dfonline.flint.actiondump.gson.ComponentGson;
import dev.dfonline.flint.actiondump.particle.ParticleType;
import dev.dfonline.flint.actiondump.potion.PotionType;
import dev.dfonline.flint.actiondump.shop.CosmeticType;
import dev.dfonline.flint.actiondump.sound.SoundCategory;
import dev.dfonline.flint.actiondump.sound.SoundType;
import dev.dfonline.flint.util.ComponentUtil;
import net.kyori.adventure.text.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record ActionDump(
        CodeBlockType[] codeblocks,
        ActionType[] actions,
        GameValueCategory[] gameValueCategories,
        GameValueType[] gameValues,
        ParticleType[] particles,
        SoundCategory[] soundCategories,
        SoundType[] sounds,
        PotionType[] potionTypes,
        CosmeticType[] cosmetics
) {
    private static class Instance {
        private static ActionDump ACTION_DUMP;
    }

    public static ActionDump reload() {
        Instance.ACTION_DUMP = null;
        return ActionDump.get();
    }

    public static ActionDump get() {
        if(Instance.ACTION_DUMP == null) {
            var gson = new GsonBuilder()
                    .registerTypeAdapter(Component.class, new ComponentGson())
                    .create();
            try {
                var file = Files.readString(ComponentUtil.ColorMode.MINI_MESSAGE.getFile().getPath());
                Instance.ACTION_DUMP = gson.fromJson(file, ActionDump.class);
            } catch (IOException e) {
                throw new ActionDumpFileMissingException();
            }
        }
        return Instance.ACTION_DUMP;
    }

}
