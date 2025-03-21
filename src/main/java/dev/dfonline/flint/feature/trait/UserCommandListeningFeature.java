package dev.dfonline.flint.feature.trait;

public interface UserCommandListeningFeature extends FeatureTrait {

    ReplaceEventResult<String> sendCommand(String message);

}
