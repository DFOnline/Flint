package dev.dfonline.flint.data;

import dev.dfonline.flint.data.value.DataValue;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.stream.Collectors;

public final class PublicBukkitValues {

    public static final String PUBLIC_BUKKIT_VALUES_KEY = "PublicBukkitValues";

    /**
     * The prefix for hypercube keys.
     *
     * @implNote This should only be used internally, if you find yourself using this,
     * you're either doing something wrong, or you're in a very specific use-case
     * in which you should make a method for it in this class.
     */
    private static final String HYPERCUBE_KEY_PREFIX = "hypercube:";

    private final NbtCompound publicBukkitValues;

    private PublicBukkitValues(NbtCompound publicBukkitValues) {
        this.publicBukkitValues = publicBukkitValues;
    }

    /**
     * Creates a new PublicBukkitValues from an ItemData.
     *
     * @param data The ItemData to create the PublicBukkitValues from.
     * @return The new PublicBukkitValues.
     * @implNote This method will return null if the ItemData does not have PublicBukkitValues.
     */
    @Nullable
    public static PublicBukkitValues fromItemData(ItemData data) {
        NbtCompound customData = data.getNbt();
        if (customData == null) {
            return null;
        }
        NbtCompound publicBukkitValues = customData.getCompound(PUBLIC_BUKKIT_VALUES_KEY);
        if (publicBukkitValues == null) {
            return null;
        }
        return new PublicBukkitValues(publicBukkitValues);
    }

    /**
     * Creates an empty PublicBukkitValues.
     *
     * @return The new empty PublicBukkitValues.
     */
    public static PublicBukkitValues getEmpty() {
        NbtCompound empty = new NbtCompound();
        empty.put(PUBLIC_BUKKIT_VALUES_KEY, new NbtCompound());
        return new PublicBukkitValues(empty);
    }

    /**
     * Gets the NbtCompound of the PublicBukkitValues.
     *
     * @return The NbtCompound of the PublicBukkitValues.
     * @apiNote This should only be used in very specific cases; the entire point of this class is to abstract the NBT data.
     */
    public NbtCompound getNbt() {
        return this.publicBukkitValues;
    }

    /**
     * Gets a String value of a hypercube key.
     *
     * @param key The key to get, without the hypercube: prefix.
     * @return The value of the key.
     */
    public String getHypercubeStringValue(String key) {
        return this.publicBukkitValues.getString(HYPERCUBE_KEY_PREFIX + key);
    }

    /**
     * Gets a String value of a key.
     *
     * @param key The key to get.
     * @return The value of the key.
     */
    public String getStringValue(String key) {
        return this.publicBukkitValues.getString(key);
    }

    /**
     * Gets a DataValue of a hypercube key.
     *
     * @param key The key to get, without the hypercube: prefix.
     * @return The value of the key.
     */
    public DataValue getHypercubeValue(String key) {
        return DataValue.fromNbt(this.publicBukkitValues.get(HYPERCUBE_KEY_PREFIX + key));
    }

    /**
     * Gets all the hypercube keys.
     *
     * @return The hypercube keys.
     */
    public Set<String> getHypercubeKeys() {
        return this.publicBukkitValues.getKeys().stream().filter(key -> key.startsWith(HYPERCUBE_KEY_PREFIX)).map(key -> key.substring(HYPERCUBE_KEY_PREFIX.length())).collect(Collectors.toSet());
    }

    /**
     * Gets all the keys.
     *
     * @return The keys.
     */
    public Set<String> getKeys() {
        return this.publicBukkitValues.getKeys();
    }

    /**
     * Checks if the PublicBukkitValues has a hypercube key.
     *
     * @param key The key to check, without the hypercube: prefix.
     * @return Whether the key exists.
     */
    public boolean hasHypercubeKey(String key) {
        return this.publicBukkitValues.contains(HYPERCUBE_KEY_PREFIX + key);
    }

    /**
     * Checks if the PublicBukkitValues has a key.
     *
     * @param key The key to check.
     * @return Whether the key exists.
     */
    public boolean hasKey(String key) {
        return this.publicBukkitValues.contains(key);
    }

    /**
     * Sets a value of a key.
     *
     * @param key   The key to set.
     * @param value The value to set.
     */
    public void setStringValue(String key, String value) {
        this.publicBukkitValues.putString(key, value);
    }

    /**
     * Sets a value of a hypercube key.
     *
     * @param key   The key to set, without the hypercube: prefix.
     * @param value The value to set.
     */
    public void setHypercubeStringValue(String key, String value) {
        this.publicBukkitValues.putString(HYPERCUBE_KEY_PREFIX + key, value);
    }

}
