package dev.dfonline.flint.template;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.dfonline.flint.template.value.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArgumentContainer {

    private final Map<Integer, SlotItem> items = new HashMap<>();

    public ArgumentContainer set(int slot, Value value) {
        this.items.put(slot, new SlotItem(value, slot));
        return this;
    }

    public Value get(int slot) {
        SlotItem item = this.items.get(slot);
        if (item != null) {
            return item.getItem();
        }
        return null;
    }

    public void remove(int slot) {
        this.items.remove(slot);
    }

    public void clear() {
        this.items.clear();
    }

    @JsonGetter("items")
    public List<SlotItem> getItems() {
        return new ArrayList<>(this.items.values());
    }

    @JsonSetter("items")
    public void setItems(List<SlotItem> items) {
        this.items.clear();
        if (items != null) {
            for (SlotItem item : items) {
                this.items.put(item.getSlot(), item);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ArgumentContainer other)) {
            return false;
        }

        if (this.items.size() != other.items.size()) {
            return false;
        }

        for (Map.Entry<Integer, SlotItem> entry : this.items.entrySet()) {
            SlotItem otherItem = other.items.get(entry.getKey());

            if (!entry.getValue().equals(otherItem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.items, this.items.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArgumentContainer{");
        for (Map.Entry<Integer, SlotItem> entry : this.items.entrySet()) {
            sb.append("slot=").append(entry.getKey()).append(", item=").append(entry.getValue()).append("; ");
        }
        sb.append('}');
        return sb.toString();
    }
}
