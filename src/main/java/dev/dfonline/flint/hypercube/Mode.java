package dev.dfonline.flint.hypercube;

public enum Mode {

    SPAWN("Spawn", false, false),
    PLAY("Play", true, false),
    DEV("Dev", true, true),
    BUILD("Build", true, true),
    CODE_SPECTATE("Code Spectate", true, false);

    private final String name;
    private final boolean inPlot;
    private final boolean isEditor;

    Mode(String name, boolean inPlot, boolean isEditor) {
        this.name = name;
        this.inPlot = inPlot;
        this.isEditor = isEditor;
    }

    public String getName() {
        return this.name;
    }

    public boolean isInPlot() {
        return this.inPlot;
    }

    public boolean isEditor() {
        return this.isEditor;
    }

}
