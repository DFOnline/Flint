package dev.dfonline.flint.templates.codeblock.target;

public enum EntityTarget {
    SELECTION("Selection"),
    DEFAULT("Default"),
    KILLER("Killer"),
    DAMAGER("Damage"),
    VICTIM("Victim"),
    SHOOTER("Shooter"),
    PROJECTILE("Projectile"),
    ALL_ENTITIES("AllEntities"),
    ALL_MOBS("AllMobs"),
    LAST_ENTITY("LastEntity"),

    NONE(""),
    ;

    public final String name;
    EntityTarget(String name) {
        this.name = name;
    }

    public static EntityTarget fromString(String target) {
        for (EntityTarget e : EntityTarget.values()) {
            if (e.name.equals(target)) {
                return e;
            }
        }
        return NONE;
    }
}
