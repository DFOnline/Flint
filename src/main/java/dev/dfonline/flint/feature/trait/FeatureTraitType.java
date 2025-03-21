package dev.dfonline.flint.feature.trait;

/**
 * Represents the type of a feature trait.
 */
public enum FeatureTraitType {
    TICKABLE(0, TickableFeature.class),
    RENDERED(1, RenderedFeature.class),
    COMMAND(2, CommandFeature.class),
    PACKET_LISTENING(3, PacketListeningFeature.class),
    USER_MESSAGE_LISTENING(4, UserMessageListeningFeature.class),
    USER_COMMAND_LISTENING(5, UserCommandListeningFeature.class);

    private final int index;
    private final Class<? extends FeatureTrait> featureClass;

    FeatureTraitType(int index, Class<? extends FeatureTrait> featureClass) {
        this.index = index;
        this.featureClass = featureClass;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends FeatureTrait> getFeatureClass() {
        return featureClass;
    }
}