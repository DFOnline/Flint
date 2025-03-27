package dev.dfonline.flint.templates;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.dfonline.flint.data.DFItem;
import dev.dfonline.flint.data.PublicBukkitValues;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class Template {
    private String name;
    private String author;
    private String version;
    private CodeBlocks blocks;

    public static @Nullable Template fromItem(ItemStack item) {
        DFItem dfItem = new DFItem(item);

        PublicBukkitValues pbvs = dfItem.getPublicBukkitValues();

        if (!pbvs.hasHypercubeKey("codetemplatedata")) {
            return null;
        }

        JsonObject data = JsonParser.parseString(pbvs.getHypercubeStringValue("codetemplatedata")).getAsJsonObject();

        return fromJson(data);
    }
    public static @Nullable Template fromJson(JsonObject data) {
        Template template = new Template();

        if (!data.has("name") || !data.has("author") || !data.has("version") || !data.has("code")) {
            return null;
        }

        template.name = data.get("name").getAsString();
        template.author = data.get("author").getAsString();
        template.version = data.get("version").getAsString();

        String blocksEncoded = data.get("code").getAsString();

        try {
            byte[] blocksBytes = Compression.fromGZIP(Compression.fromBase64(blocksEncoded.getBytes()));
            JsonObject blocks = JsonParser.parseString(new String(blocksBytes)).getAsJsonObject();
            template.blocks = CodeBlocks.fromJson(blocks);

            return template;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Template [ name = " + name + ", author = " + author + ", version = " + version + ", blocks = " + blocks + "]";
    }
}
