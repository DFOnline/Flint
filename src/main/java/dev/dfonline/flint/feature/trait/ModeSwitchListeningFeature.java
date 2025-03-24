package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.hypercube.Mode;

/**
 * A feature that listens for mode switches in DiamondFire.
 */
public interface ModeSwitchListeningFeature extends FeatureTrait {

    /**
     * Called when the user switches between modes.
     *
     * @param oldMode The mode the user was in
     * @param newMode The mode the user switched to
     */
    void onSwitchMode(Mode oldMode, Mode newMode);

}
