package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.hypercube.Mode;

public interface SwitchModeListeningFeature extends FeatureTrait {

    void onSwitchMode(Mode oldMode, Mode newMode);

}
