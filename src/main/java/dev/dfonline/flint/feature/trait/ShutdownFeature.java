package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;

/**
 * A feature that performs actions when the game is shutting down.
 */
public interface ShutdownFeature extends FeatureTrait {

    /**
     * Called when the game is shutting down.
     */
    void onShutdown();

}
