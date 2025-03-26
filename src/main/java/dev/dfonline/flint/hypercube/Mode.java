package dev.dfonline.flint.hypercube;

public enum Mode {

    /**
     * At the spawn of a node.
     */
    SPAWN("Spawn", false, false),
    /**
     * Playing a game.
     */
    PLAY("Play", true, false),
    /**
     * In dev mode, access to the entire plot region.
     */
    DEV("Dev", true, true),
    /**
     * In build mode, access to just the build area.
     */
    BUILD("Build", true, true),
    /**
     * Code spectate, a mode accessible to SrHelpers to review plots.
     */
    CODE_SPECTATE("Code Spectate", true, false),
    /**
     * No mode, the player is not on DiamondFire.
     */
    NONE("None", false, false);

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
