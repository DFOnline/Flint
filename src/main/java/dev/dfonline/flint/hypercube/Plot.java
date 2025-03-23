package dev.dfonline.flint.hypercube;

public record Plot(int id, String name, String handle, boolean whitelisted) {

    @Override
    public String toString() {
        return "Plot{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", handle='" + this.handle + '\'' +
                ", whitelisted=" + this.whitelisted +
                '}';
    }

    public String toReadableString() {
        return "ID " + this.id + ", name " + this.name + ", handle " + this.handle + ", whitelisted " + this.whitelisted;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Plot(int id1, String name1, String handle1, boolean whitelisted1)) {
            return this.id() == id1 &&
                    this.name().equals(name1) &&
                    ((this.handle() == null && handle1 == null) || this.handle().equals(handle1)) &&
                    this.whitelisted() == whitelisted1;
        }
        return false;
    }

}
