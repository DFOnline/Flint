package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.util.result.ReplaceEventResult;

public interface UserCommandListeningFeature extends FeatureTrait {

    ReplaceEventResult<String> sendCommand(String message);

}
