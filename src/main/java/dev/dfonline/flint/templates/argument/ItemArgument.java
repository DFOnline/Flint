package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import net.minecraft.item.ItemStack;

public class ItemArgument extends Argument {
    private ItemStack item;
    private String nbt; //Temporary but yeah
    public ItemArgument(JsonObject json, JsonObject data) {
        super(json);
        nbt = data.get("item").getAsString();
    }

    @Override
    public String toString() {
        return "Item [nbt=" + nbt + ", item=" + item + " " + super.toString() + "]";
    }
}
