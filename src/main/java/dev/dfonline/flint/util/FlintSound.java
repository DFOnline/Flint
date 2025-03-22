package dev.dfonline.flint.util;

import dev.dfonline.flint.util.message.impl.SoundMessage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

/**
 * Represents a Minecraft sound with a pre-configured volume, pitch, and source.
 * <p>
 * Example:
 * <pre>
 *         FlintSound sound = FlintSound.builder()
 *          .soundEvent(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP)
 *          .volume(1.0F)
 *          .pitch(2.0F)
 *          .source(SoundCategory.RECORDS)
 *          .build();
 *     </pre>
 * An FlintSound cannot be played directly,
 * it must be wrapped in a {@link SoundMessage MessageSoundComponent} and sent as a message.
 * </p>
 */
public final class FlintSound {

    private final SoundEvent soundEvent;
    private final float volume;
    private final float pitch;
    private final SoundCategory source;

    public FlintSound(Builder builder) {
        this.soundEvent = builder.sound;
        this.volume = builder.volume;
        this.pitch = builder.pitch;
        this.source = builder.source;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    public SoundCategory getSource() {
        return this.source;
    }

    public static class Builder {

        private SoundEvent sound;
        private float volume = 1.0F;
        private float pitch = 1.0F;
        private SoundCategory source = SoundCategory.MASTER;

        public Builder setSound(SoundEvent sound) {
            this.sound = sound;
            return this;
        }

        public Builder setVolume(float volume) {
            this.volume = volume;
            return this;
        }

        public Builder setPitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public Builder setSource(SoundCategory source) {
            this.source = source;
            return this;
        }

        public FlintSound build() {
            return new FlintSound(this);
        }

    }

}
