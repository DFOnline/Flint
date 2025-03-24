package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.ReplacementEventResult;

/**
 * A feature that listens for user commands before they are sent to the server.
 */
public interface UserCommandListeningFeature extends FeatureTrait {

    /**
     * Called when the user sends a command.
     *
     * @param message The command message being sent
     * @return The replacement event result which can modify or cancel the command
     */
    ReplacementEventResult<String> sendCommand(String message);

}
