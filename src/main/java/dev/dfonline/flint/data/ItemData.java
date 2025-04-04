package dev.dfonline.flint.data;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class ItemData {

    private NbtCompound customData;
    private PublicBukkitValues publicBukkitValues;

    /**
     * Creates a new ItemData from an ItemStack.
     *
     * @param item The item to create the ItemData from.
     * @implNote Most operations won't work if the item doesn't have custom data.
     */
    public ItemData(ItemStack item) {
        NbtComponent customDataComponent = item.get(DataComponentTypes.CUSTOM_DATA);
        if (customDataComponent != null) {
            this.customData = customDataComponent.copyNbt();
        }
    }

    /**
     * Creates an ItemData with an existing empty NbtCompound.
     */
    private ItemData() {
        this.customData = new NbtCompound();
    }

    /**
     * Gets the NBT Compound of the CUSTOM_DATA item component, applying the PublicBukkitValues along the way.
     *
     * @return The NBT Compound of the CUSTOM_DATA item component.
     * @apiNote This should only be used in very specific cases; the entire point of this class is to abstract the NBT data.
     */
    public NbtCompound getNbt() {
        // Should only be used in very specific cases, the entire point of this class is to abstract the NBT data.
        if (this.customData == null) {
            this.customData = new NbtCompound();
        }
        if (this.publicBukkitValues != null) {
            this.customData.put(PublicBukkitValues.PUBLIC_BUKKIT_VALUES_KEY, this.publicBukkitValues.getNbt());
        }
        return this.customData;
    }

    /**
     * Creates an empty ItemData.
     */
    public static ItemData getEmpty() {
        return new ItemData();
    }

    /**
     * Checks if the item data has custom data.
     *
     * @return Whether the item data has custom data.
     */
    public boolean hasCustomData() {
        return this.customData != null;
    }

    /**
     * Gets the PublicBukkitValues from the item data.
     *
     * @return The PublicBukkitValues.
     */
    @Nullable
    public PublicBukkitValues getPublicBukkitValues() {
        if (this.publicBukkitValues == null) {
            this.publicBukkitValues = PublicBukkitValues.fromItemData(this);
        }
        return this.publicBukkitValues;
    }

    /**
     * Sets the PublicBukkitValues of the item data.
     *
     * @param publicBukkitValues The PublicBukkitValues to set.
     */
    public void setPublicBukkitValues(PublicBukkitValues publicBukkitValues) {
        this.publicBukkitValues = publicBukkitValues;
    }

    /**
     * Removes a key from the custom data.
     *
     * @param key The key to remove.
     */
    public void removeKey(String key) {
        this.customData.remove(key);
    }

    /**
     * Gets a String value of a key.
     *
     * @param key The key to get.
     * @return The value of the key, or an empty string if it doesn't exist.
     */
    public String getStringValue(String key) {
        return this.customData.getString(key);
    }

    /**
     * Gets a String value of a key.
     *
     * @param key   The key to get.
     * @param value The value of the key.
     */
    public void setStringValue(String key, String value) {
        this.customData.putString(key, value);
    }

    /**
     * Checks if the custom data has a key.
     *
     * @param key The key to check.
     * @return Whether the custom data has the key.
     */
    public boolean hasKey(String key) {
        return this.customData.contains(key);
    }

    /**
     * Delegates to {@link PublicBukkitValues#getHypercubeStringValue(String)}.
     *
     * @param key The key to get, without the hypercube: prefix.
     * @return The value of the key, or an empty string if it doesn't exist.
     */
    public String getHypercubeStringValue(String key) {
        PublicBukkitValues pbv = this.getPublicBukkitValues();
        if (pbv == null) {
            return "";
        }
        return pbv.getHypercubeStringValue(key);
    }

    /**
     * Delegates to {@link PublicBukkitValues#setHypercubeStringValue(String, String)}.
     *
     * @param key   The key to set, without the hypercube: prefix.
     * @param value The value to set.
     */
    public void setHypercubeStringValue(String key, String value) {
        PublicBukkitValues pbv = this.getPublicBukkitValues();
        if (pbv == null) {
            pbv = PublicBukkitValues.getEmpty();
        }
        pbv.setHypercubeStringValue(key, value);
    }

    /**
     * Delegates to {@link PublicBukkitValues#hasHypercubeKey(String)}.
     *
     * @param key The key to check, without the hypercube: prefix.
     * @return Whether the key exists.
     */
    public boolean hasHypercubeKey(String key) {
        PublicBukkitValues bpv = this.getPublicBukkitValues();
        if (bpv == null) {
            return false;
        }
        return bpv.hasHypercubeKey(key);
    }

    /**
     * Converts the item data to a NbtComponent.
     *
     * @return The NbtComponent.
     * @apiNote This should only be used in very specific cases; the entire point of this class is to abstract the NBT data.
     */
    public NbtComponent toComponent() {
        return NbtComponent.of(this.getNbt());
    }

}
