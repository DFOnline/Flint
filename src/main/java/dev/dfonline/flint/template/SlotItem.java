package dev.dfonline.flint.template;

import dev.dfonline.flint.template.value.Value;

import java.util.Objects;

public final class SlotItem {

    private Value item;
    private int slot;

    public SlotItem() {
    }

    public SlotItem(Value item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public Value getItem() {
        return this.item;
    }

    public void setItem(Value item) {
        this.item = item;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SlotItem other)) {
            return false;
        }

        if (this.slot != other.slot) {
            return false;
        }

        if (this.item == null) {
            return other.item == null;
        }

        return this.item.equals(other.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.slot, this.item);
    }

    @Override
    public String toString() {
        return "SlotItem{" +
                "item=" + this.item +
                ", slot=" + this.slot +
                '}';
    }

}
