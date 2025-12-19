package dev.dfonline.flint.hypercube;

import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.Objects;

public class Plot {

    private final int id;
    private final Text name;
    private final String handle;
    private final boolean whitelisted;
    private Vec3i devOrigin;
    private PlotSize size;
    private boolean hasUnderground = false;
    private final String owner;

    public Plot(int id, Text name, String handle, boolean whitelisted, String owner) {
        this.id = id;
        this.name = name;
        this.handle = handle;
        this.whitelisted = whitelisted;
        this.owner = owner;
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

    public Vec3i getDevOrigin() {
        return this.devOrigin;
    }

    public void setDevOrigin(Vec3i origin) {
        this.devOrigin = origin;
    }

    public PlotSize getSize() {
        return this.size;
    }

    public void setSize(PlotSize size) {
        this.size = size;
    }

    public boolean hasUnderground() {
        return this.hasUnderground;
    }

    public void setHasUnderground(boolean hasUnderground) {
        this.hasUnderground = hasUnderground;
    }

    public String getOwner() {
        return this.owner;
    }

    public boolean isPosInCodeSpace(BlockPos pos) {
        int x = pos.getX();
        int z = pos.getZ();

        return x < this.devOrigin.getX()
                && x >= this.devOrigin.getX() - this.size.getCodeWidth()
                && z >= this.devOrigin.getZ()
                && z <= this.devOrigin.getZ() + this.size.getCodeLength();
    }

    public String toReadableString() {
        return "ID " + this.id + ", name " + this.name.getString() + ", handle " + this.handle + ", whitelisted " + this.whitelisted + ", origin " + this.devOrigin + ", owner " + this.owner;
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
                    this.whitelisted == plot.isWhitelisted() &&
                    this.owner.equals(plot.getOwner());
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Origin might be null, but it's still the same plot, so we don't include it in the hash code.
        return Objects.hash(this.id, this.name, this.handle, this.whitelisted, this.owner);
    }

}
