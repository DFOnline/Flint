package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import org.apache.commons.lang3.StringUtils;

public class PotionArgument extends Argument {
    public enum PotionType {
        ABSORPTION,
        CONDUIT_POWER,
        DOLPHINS_GRACE("Dolphin's Grace"),
        FIRE_RESISTANCE,
        HEALTH_BOOST,
        HASTE,
        HERO_OF_THE_VILLAGE("Hero of the Village"),
        INSTANT_HEALTH,
        JUMP_BOOST,
        INVISIBILITY,
        LUCK,
        NIGHT_VISION,
        REGENERATION,
        RESISTANCE,
        SATURATION,
        SLOW_FALLING,
        STRENGTH,
        SPEED,
        WATER_BREATHING,

        BAD_OMEN,
        BLINDNESS,
        DARKNESS,
        GLOWING,
        HUNGER,
        INFESTED,
        INSTANT_DAMAGE,
        LEVITATION,
        MINING_FATIGUE,
        NAUSEA,
        OOZING,
        POISON,
        RAID_OMEN,
        SLOWNESS,
        TRIAL_OMEN,
        BAD_LUCK,
        WEAKNESS,
        WEAVING,
        WIND_CHARGED,
        WITHER
        ;

        public final String name;
        PotionType(String name) {
            this.name = name;
        }
        PotionType() {
            this.name = StringUtils.capitalize(this.name().replaceAll("_", " ").toLowerCase());
        }

        public String getName() {
            return name;
        }

        public static PotionType getFromString(String string) {
            for (PotionType potionType : PotionType.values()) {
                if (string.equals(potionType.name)) {
                    return potionType;
                }
            }
            return null;
        }
    }

    private PotionType type;

    public PotionType getType() {
        return type;
    }

    public void setType(PotionType type) {
        this.type = type;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    private int ticks; // In Ticks
    private int amplifier;

    public PotionArgument(JsonObject json, JsonObject data) {
        super(json);
        type = PotionType.getFromString(data.get("pot").getAsString());
        ticks = data.get("dur").getAsInt();
        amplifier = data.get("amp").getAsInt() + 1;
    }

    public PotionArgument(int slot, PotionType type, int amplifier, int ticks) {
        super(slot);
        this.type = type;
        this.amplifier = amplifier;
        this.ticks = ticks;
    }

    @Override
    protected JsonObject getData() {
        JsonObject data = new JsonObject();
        data.addProperty("pot", type.getName());
        data.addProperty("dur", ticks);
        data.addProperty("amp", amplifier - 1);
        return data;
    }

    @Override
    public String getID() {
        return "pot";
    }

    @Override
    public String toString() {
        return "Potion [type=" + type + ", ticks=" + ticks + ", amplifier=" + amplifier + " " + super.toString() + "]";
    }
}
