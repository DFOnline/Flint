package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.trait.results.ReplaceEventResult;

public interface UserMessageListeningFeature extends FeatureTrait {

    ReplaceEventResult<String> sendMessage(String message);

}
