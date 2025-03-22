package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.util.result.ReplaceEventResult;

public interface UserMessageListeningFeature extends FeatureTrait {

    ReplaceEventResult<String> sendMessage(String message);

}
