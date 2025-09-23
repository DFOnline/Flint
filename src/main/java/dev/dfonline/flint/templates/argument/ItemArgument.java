package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.DataResult;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;

public class ItemArgument extends Argument {
    private ItemStack item;
    public ItemArgument(JsonObject json, JsonObject data) {
        super(json);
        var nbt = data.get("item").getAsString();
        try {
            assert Flint.getClient().world != null;
            setNBT(nbt);
        } catch (Exception e) {
            item = null;
        }
    }

    public String getNBT() {
        var registries = Flint.getClient().world.getRegistryManager();
        RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, registries);
        DataResult<NbtElement> dr = ItemStack.CODEC.encodeStart(ops, item);
        NbtElement el = dr.result().orElseThrow(() -> new RuntimeException("Failed to encode ItemStack: " + dr.error().map(Object::toString).orElse("unknown")));
        return el.toString();
    }

    public void setNBT(String nbt) {
        var registries = Flint.getClient().world.getRegistryManager();
        try {
            NbtElement el = StringNbtReader.readCompound(nbt);
            RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, registries);
            DataResult<ItemStack> dr = ItemStack.CODEC.parse(ops, el);
            item = dr.result().orElse(ItemStack.EMPTY);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ItemStack: " + nbt, e);
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
        data.addProperty("item", getNBT());
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
