package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.DataResult;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.item.ItemStack;

public class ItemArgument extends Argument {
    private ItemStack item;

    public ItemArgument(JsonObject json, JsonObject data) {
        super(json);
        var nbt = data.get("item").getAsString();
        try {
            assert Flint.getClient().level != null;
            setNBT(nbt);
        } catch (Exception e) {
            item = null;
        }
    }

    public String getNBT() {
        return ItemStack.CODEC.encodeStart(
                Flint.getClient().player.registryAccess().createSerializationContext(NbtOps.INSTANCE), item)
            .getOrThrow(str -> new RuntimeException("Failed to parse item into NBT for templates: %s".formatted(str)))
            .toString();
    }

    public void setNBT(String nbt) {
        try {
            CompoundTag nbtCompound = TagParser.parseCompoundFully(nbt);
            item = ItemStack.CODEC.decode(
                    Flint.getClient().player.registryAccess().createSerializationContext(NbtOps.INSTANCE), nbtCompound)
                .getOrThrow(str -> new RuntimeException("Failed to parse NBT into item for templates: %s".formatted(str)))
                .getFirst()
            ;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse item into NBT for templates: %s".formatted(nbt), e);
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
        assert Flint.getClient().level != null;
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
