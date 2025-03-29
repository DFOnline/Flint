package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.template.value.Value;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemValue extends Value {

    private final Map<String, String> data = new HashMap<>();

    public ItemValue() {
        this.setId("item");
    }

    public ItemValue(String nbt) {
        this();
        this.data.put("item", nbt);
    }

    public ItemValue(ItemStack item) {
        this();
        ClientWorld world = Flint.getClient().world;
        if (world == null) {
            throw new IllegalStateException("World is null, cannot create ItemValue from ItemStack");
        }
        this.data.put("item", item.toNbt(world.getRegistryManager()).toString());
    }

    @JsonGetter("data")
    public Map<String, String> getData() {
        return this.data;
    }

    @JsonSetter("data")
    public void setData(Map<String, String> data) {
        this.data.clear();
        if (data != null) {
            this.data.putAll(data);
        }
    }

    public String getItem() {
        return this.data.get("item");
    }

    @JsonIgnore
    public void setItem(String value) {
        this.data.put("item", value);
    }

}
