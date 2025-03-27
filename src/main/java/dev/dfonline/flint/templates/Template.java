package dev.dfonline.flint.templates;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.data.DFItem;
import dev.dfonline.flint.data.PublicBukkitValues;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import static net.minecraft.text.Text.literal;

public class Template {
    private String name;
    private String author;
    private String version;
    private CodeBlocks blocks;
    private Item material;

    public static @Nullable Template fromItem(ItemStack item) {
        DFItem dfItem = new DFItem(item);
        PublicBukkitValues pbvs = dfItem.getPublicBukkitValues();

        if (!pbvs.hasHypercubeKey("codetemplatedata")) {
            return null;
        }

        JsonObject data = JsonParser.parseString(pbvs.getHypercubeStringValue("codetemplatedata")).getAsJsonObject();

        return fromJson(data, item.getItem());
    }

    public ItemStack toItem() {
        ItemStack item = new ItemStack(material);
        DFItem dfItem = new DFItem(item);
        dfItem.getPublicBukkitValues().setHypercubeStringValue("codetemplatedata", toJson().toString());
        dfItem.setName(literal(name));
        return dfItem.getItemStack();
    }

    public static @Nullable Template fromJson(JsonObject data, Item item) {
        Template template = new Template();
        template.material = item;
        if (!data.has("name") || !data.has("author") || !data.has("version") || !data.has("code")) {
            return null;
        }

        template.name = data.get("name").getAsString();
        template.author = data.get("author").getAsString();
        template.version = data.get("version").getAsString();

        String blocksEncoded = data.get("code").getAsString();

        try {
            byte[] blocksBytes = Compression.fromGZIP(Compression.fromBase64(blocksEncoded.getBytes()));
            var blocks = JsonParser.parseString(new String(blocksBytes)).getAsJsonObject().getAsJsonArray("blocks");
            template.blocks = CodeBlocks.fromJson(blocks);

            return template;
        } catch (IOException e) {
            return null;
        }
    }
    public JsonObject toJson() {
        JsonObject template = new JsonObject();
        template.addProperty("name", name);
        template.addProperty("author", author);
        template.addProperty("version", version);
        template.addProperty("code", getEncodedCode());

        return template;
    }

    private String getEncodedCode() {
        JsonObject code = getCodeJson();
        try {
            return new String(Compression.toBase64(Compression.toGZIP(code.toString().getBytes())));
        } catch (IOException e) {
            return null;
        }
    }

    private JsonObject getCodeJson() {
        JsonObject code = new JsonObject();
        JsonArray blocks = this.blocks.getJson();

        code.add("blocks", blocks);
        return code;
    }

    public void printToChat() {
        print("Template: ");
        print("Name: " + name);
        print("Author: " + author);
        print("Version: " + version);
        blocks.printToChat();
    }

    static void print(String string) {
        Flint.getUser().getPlayer().sendMessage(literal(string), false);
    }

    @Override
    public String toString() {
        return "Template [ name = " + name + ", author = " + author + ", version = " + version + ", blocks = " + blocks + "]";
    }

    public String toReadableJson() {
        return getCodeJson().toString();
    }
}
