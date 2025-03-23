package dev.dfonline.flint.hypercube;

import net.minecraft.util.math.Vec3d;

public class Plot {
    public String toReadableString() {
        return "ID " + this.id + ", name " + this.name + ", handle " + this.handle + ", whitelisted " + this.whitelisted;
    }

    private final int id;
    private final String name;
    private final String handle;
    private final boolean whitelisted;
    private Vec3d origin;

    public Plot(int id, String name, String handle, boolean whitelisted) {
        this.id = id;
        this.name = name;
        this.handle = handle;
        this.whitelisted = whitelisted;
        origin = null;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getHandle() {
        return handle;
    }
    public boolean isWhitelisted() {
        return whitelisted;
    }

    public Vec3d getOrigin() {
        return origin;
    }

    public void setOrigin(Vec3d origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Plot plot) {
            if (handle == null && plot.getHandle() != null || plot.getHandle() == null && handle != null) {
                return false;
            }
            return id == plot.getId() &&
                    name.equals(plot.getName()) &&
                    ((handle == null && plot.getHandle() == null)|| handle.equals(plot.getHandle())) &&
                    whitelisted == plot.isWhitelisted();
        }
        return false;
    }


}
