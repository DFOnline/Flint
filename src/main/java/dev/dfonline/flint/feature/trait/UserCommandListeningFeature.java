package dev.dfonline.flint.feature.trait;

import java.util.Optional;

public interface UserCommandListeningFeature extends FeatureTrait {
    ReplaceEventResult<String> sendCommand(String message);
}
