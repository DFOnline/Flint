package dev.dfonline.flint.templates.codeblock.target;

public enum EntityTarget {
    SELECTION,
    DEFAULT,
    KILLER,
    DAMAGER,
    VICTIM,
    SHOOTER,
    PROJECTILE,
    ALL_ENTITIES,
    ALL_MOBS,
    LAST_ENTITY,

    NONE,
    ;


    public static EntityTarget fromString(String target) {
        return switch (target) {
            case "Selection" -> SELECTION;
            case "Default" -> DEFAULT;
            case "Killer" -> KILLER;
            case "Damage" -> DAMAGER;
            case "Shooter" -> SHOOTER;
            case "Victim" -> VICTIM;
            case "AllEntities" -> ALL_ENTITIES;
            case "AllMobs" -> ALL_MOBS;
            case "LastEntity" -> LAST_ENTITY;
            default -> NONE;
        };
    }
}
