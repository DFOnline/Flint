package dev.dfonline.flint.templates.codeblock.target;

public enum PlayerTarget {
    SELECTION("Selection"),
    DEFAULT("Default"),
    KILLER("Killer"),
    DAMAGER("Damage"),
    SHOOTER("Shooter"),
    VICTIM("Victim"),
    ALL("AllPlayers"),

    NONE(""),
    ;

    public final String name;
    PlayerTarget(String name) {
        this.name = name;
    }

    public static PlayerTarget fromString(String target) {
        for (PlayerTarget t : PlayerTarget.values()) {
            if (t.name.equals(target)) {
                return t;
            }
        }
        return NONE;
    }
}
