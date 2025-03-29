package dev.dfonline.flint.template.value.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import dev.dfonline.flint.template.value.Value;

public class PotionValue extends Value {

    private PotionData data;

    public PotionValue() {
        this.setId("pot");
    }

    public PotionValue(String potion, int duration, int amplifier) {
        this();
        this.data = new PotionData(potion, duration, amplifier);
    }

    public PotionData getData() {
        return this.data;
    }

    public void setData(PotionData data) {
        this.data = data;
    }

    public static class PotionData {

        private String potion;
        private int duration;
        private int amplifier;

        public PotionData() {
        }

        public PotionData(String potion, int duration, int amplifier) {
            this.potion = potion;
            this.duration = duration;
            this.amplifier = amplifier;
        }

        @JsonGetter("pot")
        public String getPotion() {
            return this.potion;
        }

        public void setPotion(String potion) {
            this.potion = potion;
        }

        @JsonGetter("dur")
        public int getDuration() {
            return this.duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        @JsonGetter("amp")
        public int getAmplifier() {
            return this.amplifier;
        }

        public void setAmplifier(int amplifier) {
            this.amplifier = amplifier;
        }

    }

}
