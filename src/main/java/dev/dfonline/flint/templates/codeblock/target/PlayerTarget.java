package dev.dfonline.flint.templates.codeblock.target;

public enum PlayerTarget {
    SELECTION,
    DEFAULT,
    KILLER,
    DAMAGER,
    SHOOTER,
    VICTIM,
    ALL,

    NONE,
    ;

    public static PlayerTarget fromString(String target) {
        return switch (target) {
            case "Selection" -> SELECTION;
            case "Default" -> DEFAULT;
            case "Killer" -> KILLER;
            case "Damage" -> DAMAGER;
            case "Shooter" -> SHOOTER;
            case "Victim" -> VICTIM;
            case "AllPlayers" -> ALL;
            default -> NONE;
        };
    }
}
