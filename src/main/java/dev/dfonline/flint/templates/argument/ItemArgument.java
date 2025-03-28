package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;

public class ItemArgument extends Argument {
    private ItemStack item;
    public ItemArgument(JsonObject json, JsonObject data) {
        super(json);
        var nbt = data.get("item").getAsString();
        try {
            assert Flint.getClient().world != null;
            item = ItemStack.fromNbt(Flint.getClient().world.getRegistryManager(), StringNbtReader.parse(nbt)).get();
        } catch (Exception e) {
            item = null;
        }
    }

    public ItemArgument(int slot, ItemStack item) {
        super(slot);
        this.item = item;
    }

    @Override
    public String toString() {
        return "Item [item=" + item + " " + super.toString() + "]";
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        assert Flint.getClient().world != null;
        data.addProperty("item", this.item.toNbt(Flint.getClient().world.getRegistryManager()).asString());
        return data;
    }

    @Override
    public String getID() {
        return "item";
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
