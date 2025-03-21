package dev.dfonline.flint.feature.trait;

public interface UserMessageListeningFeature extends FeatureTrait {
    ReplaceEventResult<String> sendMessage(String message);
}
