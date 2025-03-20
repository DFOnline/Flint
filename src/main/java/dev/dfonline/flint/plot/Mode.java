package dev.dfonline.flint.plot;

public enum Mode {

    SPAWN(false, false),
    PLAY(true, false),
    DEV(true, true),
    BUILD(true, true);

    private final boolean inPlot;
    private final boolean isEditor;

    Mode(boolean inPlot, boolean isEditor) {
        this.inPlot = inPlot;
        this.isEditor = isEditor;
    }

    public boolean isInPlot() {
        return inPlot;
    }

    public boolean isEditor() {
        return isEditor;
    }

}
