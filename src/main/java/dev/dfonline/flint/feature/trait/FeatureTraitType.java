package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.trait.results.TooltipRenderFeature;

/**
 * Represents the type of feature trait.
 */
public enum FeatureTraitType {

    TICKED(0, TickedFeature.class),
    RENDERED(1, RenderedFeature.class),
    COMMAND(2, CommandFeature.class),
    PACKET_LISTENING(3, PacketListeningFeature.class),
    USER_MESSAGE_LISTENING(4, UserMessageListeningFeature.class),
    USER_COMMAND_LISTENING(5, UserCommandListeningFeature.class),
    WORLD_RENDER(6, WorldRenderFeature.class),
    TOOLTIP_RENDER(7, TooltipRenderFeature.class),

    GAME_MESSAGE_LISTENING(9, GameMessageListeningFeature.class);

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
