package dev.dfonline.flint.feature.trait;

/**
 * Represents the type of feature trait.
 */
public enum FeatureTraitType {

    TICKED(0, TickedFeature.class),
    RENDERED(1, RenderedFeature.class),
    COMMAND(2, CommandFeature.class),
    PACKET_LISTENING(3, PacketListeningFeature.class);

    private final int index;
    private final Class<? extends FeatureTrait> featureClass;

    FeatureTraitType(int index, Class<? extends FeatureTrait> featureClass) {
        this.index = index;
        this.featureClass = featureClass;
    }

    public int getIndex() {
        return this.index;
    }

    public Class<? extends FeatureTrait> getFeatureClass() {
        return this.featureClass;
    }

}
