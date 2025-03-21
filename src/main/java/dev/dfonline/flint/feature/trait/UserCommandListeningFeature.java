package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.trait.results.ReplaceEventResult;

public interface UserCommandListeningFeature extends FeatureTrait {

    ReplaceEventResult<String> sendCommand(String message);

}
