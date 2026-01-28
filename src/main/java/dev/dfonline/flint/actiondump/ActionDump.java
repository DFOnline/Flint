package dev.dfonline.flint.actiondump;

import com.google.gson.*;
import dev.dfonline.flint.actiondump.codeblocks.ActionType;
import dev.dfonline.flint.actiondump.codeblocks.CodeBlockType;
import dev.dfonline.flint.actiondump.categories.ValueCategory;
import dev.dfonline.flint.actiondump.gamevalues.GameValueType;
import dev.dfonline.flint.actiondump.gson.ComponentGson;
import dev.dfonline.flint.actiondump.particle.ParticleType;
import dev.dfonline.flint.actiondump.potion.PotionType;
import dev.dfonline.flint.actiondump.shop.CosmeticType;
import dev.dfonline.flint.actiondump.sound.SoundType;
import dev.dfonline.flint.util.ComponentUtil;
import net.kyori.adventure.text.Component;

import java.io.IOException;
import java.nio.file.Files;

public record ActionDump(
        CodeBlockType[] codeblocks,
        ActionType[] actions,
        ValueCategory[] gameValueCategories,
        GameValueType[] gameValues,
        ValueCategory[] particleCategories,
        ParticleType[] particles,
        ValueCategory[] soundCategories,
        SoundType[] sounds,
        PotionType[] potions,
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
