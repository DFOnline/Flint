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

}
