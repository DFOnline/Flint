package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.ReplacementEventResult;

public interface UserCommandListeningFeature extends FeatureTrait {

    ReplacementEventResult<String> sendCommand(String message);

}
