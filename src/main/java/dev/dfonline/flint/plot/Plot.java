package dev.dfonline.flint.plot;

public record Plot(String id) {

    @Override
    public String toString() {
        return "Plot{" +
                "id='" + id + '\'' +
                '}';
    }

}
