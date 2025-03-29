package dev.dfonline.flint.template.value.impl;

import dev.dfonline.flint.template.value.Value;

public class SoundValue extends Value {

    private SoundData data;

    public SoundValue() {
        this.setId("snd");
    }

    public SoundValue(String sound, float pitch, float volume) {
        this();
        this.data = new SoundData(sound, pitch, volume);
    }

    public SoundData getData() {
        return this.data;
    }

    public void setData(SoundData data) {
        this.data = data;
    }

    public static class SoundData {
        private String sound;
        private float pitch;
        private float vol;

        public SoundData() {
        }

        public SoundData(String sound, float pitch, float vol) {
            this.sound = sound;
            this.pitch = pitch;
            this.vol = vol;
        }

        public float getPitch() {
            return this.pitch;
        }

        public void setPitch(float pitch) {
            this.pitch = pitch;
        }

        public float getVol() {
            return this.vol;
        }

        public void setVol(float vol) {
            this.vol = vol;
        }

        public String getSound() {
            return this.sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }
    }

}
