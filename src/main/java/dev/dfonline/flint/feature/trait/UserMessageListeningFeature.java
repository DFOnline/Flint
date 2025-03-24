package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.ReplacementEventResult;

/**
 * A feature that listens for user messages before they are sent to the server.
 */
public interface UserMessageListeningFeature extends FeatureTrait {

    /**
     * Called when the user sends a message.
     *
     * @param message The message being sent
     * @return The replacement event result which can modify or cancel the message
     */
    ReplacementEventResult<String> sendMessage(String message);

}
