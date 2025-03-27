package dev.dfonline.flint.templates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.dfonline.flint.Flint;

import java.util.ArrayList;

import static dev.dfonline.flint.templates.Template.print;
import static net.minecraft.text.Text.literal;

public class CodeBlocks {
    private ArrayList<CodeBlock> blocks;

    public static CodeBlocks fromJson(JsonArray blocks) {
        CodeBlocks codeBlocks = new CodeBlocks();

        Flint.getUser().getPlayer().sendMessage(literal(blocks.toString()), false);

        codeBlocks.blocks = new ArrayList<>();
        for (JsonElement block : blocks) {
            JsonObject blockJson = block.getAsJsonObject();
            codeBlocks.blocks.add(CodeBlock.fromJson(blockJson.getAsJsonObject()));
        }

        return codeBlocks;
    }

    @Override
    public String toString() {
        return "CodeBlocks [blocks = " + blocks + "]";
    }

    public void printToChat() {
        print("CodeBlocks: ");
        for (CodeBlock block : blocks) {
            if (block != null) {
                block.printToChat();
            } else {
                print("null");
            }
        }
    }

    public JsonArray getJson() {
        JsonArray blocks = new JsonArray();
        for (var block : this.blocks) {
            blocks.add(block.toJSON());
        }
        return blocks;
    }
}
