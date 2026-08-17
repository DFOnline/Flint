package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.ReplacementEventResult;
import net.minecraft.network.chat.Component;

/**
 * A feature that listens for chat messages, allowing to cancel or replace them.
 */
public interface ChatListeningFeature extends FeatureTrait {

    /**
     * Called when a chat message is received.
     *
     * @param text      The text message that was received
     * @param actionbar Whether the message was displayed in the action bar
     * @return The result of the event
     */
    ReplacementEventResult<Component> onChatMessage(Component text, boolean actionbar);

}
