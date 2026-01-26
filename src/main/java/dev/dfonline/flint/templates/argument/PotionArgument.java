package dev.dfonline.flint.templates.argument;

import com.google.gson.JsonObject;
import dev.dfonline.flint.templates.argument.abstracts.Argument;
import org.apache.commons.lang3.StringUtils;

public class PotionArgument extends Argument {
    public enum PotionType {
        ABSORPTION("Absorption"),
        CONDUIT_POWER("Conduit Power"),
        DOLPHINS_GRACE("Dolphin's Grace"),
        FIRE_RESISTANCE("Fire Resistance"),
        HEALTH_BOOST("Health Boost"),
        HASTE("Haste"),
        HERO_OF_THE_VILLAGE("Hero of the Village"),
        INSTANT_HEALTH("Instant Health"),
        JUMP_BOOST("Jump Boost"),
        INVISIBILITY("Invisibility"),
        LUCK("Luck"),
        NIGHT_VISION("Night Vision"),
        REGENERATION("Regeneration"),
        RESISTANCE("Resistance"),
        SATURATION("Saturation"),
        SLOW_FALLING("Slow Falling"),
        STRENGTH("Strength"),
        SPEED("Speed"),
        WATER_BREATHING("Water Breathing"),

        BAD_OMEN("Bad Omen"),
        BLINDNESS("Blindness"),
        DARKNESS("Darkness"),
        GLOWING("Glowing"),
        HUNGER("Hunger"),
        INFESTED("Infested"),
        INSTANT_DAMAGE("Instant Damage"),
        LEVITATION("Levitation"),
        MINING_FATIGUE("Mining Fatigue"),
        NAUSEA("Nausea"),
        OOZING("Oozing"),
        POISON("Poison"),
        RAID_OMEN("Raid Omen"),
        SLOWNESS("Slowness"),
        TRIAL_OMEN("Trial Omen"),
        BAD_LUCK("Bad Luck"),
        WEAKNESS("Weakness"),
        WEAVING("Weaving"),
        WIND_CHARGED("Wind Charged"),
        WITHER("Wither")
        ;

        public final String dfName;
        PotionType(String name) {
            this.dfName = name;
        }

        public String getName() {
            return dfName;
        }

        public static PotionType getFromString(String string) {
            for (PotionType potionType : PotionType.values()) {
                if (string.equals(potionType.dfName)) {
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
