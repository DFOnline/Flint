package dev.dfonline.flint.hypercube;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class Plot {

    private final int id;
    private final Text name;
    private final String handle;
    private final boolean whitelisted;
    private Vec3d origin;

    public Plot(int id, Text name, String handle, boolean whitelisted) {
        this.id = id;
        this.name = name;
        this.handle = handle;
        this.whitelisted = whitelisted;
        this.origin = null;
    }

    public int getId() {
        return this.id;
    }

    public Text getName() {
        return this.name;
    }

    public String getHandle() {
        return this.handle;
    }

    public boolean isWhitelisted() {
        return this.whitelisted;
    }

    public Vec3d getOrigin() {
        return this.origin;
    }

    public void setOrigin(Vec3d origin) {
        this.origin = origin;
    }

    public String toReadableString() {
        return "ID " + this.id + ", name " + ((TextComponent) this.name).content() + ", handle " + this.handle + ", whitelisted " + this.whitelisted + ", origin " + this.origin;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Plot plot) {
            if (this.handle == null && plot.getHandle() != null || plot.getHandle() == null && this.handle != null) {
                return false;
            }

            return this.id == plot.getId() &&
                    this.name.equals(plot.getName()) &&
                    (this.handle == null && plot.getHandle() == null || this.handle != null && this.handle.equals(plot.getHandle())) &&
                    this.whitelisted == plot.isWhitelisted();
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Origin might be null, but it's still the same plot, so we don't include it in the hash code.
        return Objects.hash(this.id, this.name, this.handle, this.whitelisted);
    }

}
