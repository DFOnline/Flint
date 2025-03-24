package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;

/**
 * Gets called when the client ticks.
 */
public interface TickedFeature extends FeatureTrait {

    /**
     * Called every tick.
     */
    void tick();

}
