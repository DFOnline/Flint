package dev.dfonline.flint.hypercube;

public enum PlotSize {

    BASIC(50),
    LARGE(100),
    MASSIVE(300),
    MEGA(1000, 300, 300);

    private final int size;
    private final int codeLength;
    private final int codeWidth;

    PlotSize(int size) {
        this.size = size;
        this.codeLength = size;
        this.codeWidth = 20;
    }

    PlotSize(int size, int codeLength, int codeWidth) {
        this.size = size;
        this.codeLength = codeLength;
        this.codeWidth = codeWidth;
    }

    public int getSize() {
        return this.size;
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public int getCodeWidth() {
        return this.codeWidth;
    }

}
