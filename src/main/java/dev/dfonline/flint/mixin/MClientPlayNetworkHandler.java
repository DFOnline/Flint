package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.UserMessageListeningFeature;
import dev.dfonline.flint.util.result.ReplacementEventResult;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class MClientPlayNetworkHandler {

    @Unique
    private boolean sending = false;

    @Inject(method = "sendChat", at = @At("HEAD"), cancellable = true)
    private void sendChatMessage(String message, CallbackInfo ci) {
        if (this.sending) {
            return;
        }

        String newMessage = null;

        for (FeatureTrait trait : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.USER_MESSAGE_LISTENING)) {
            ReplacementEventResult<String> result = ((UserMessageListeningFeature) trait).sendMessage(message);
            if (result.getType() == ReplacementEventResult.Type.CANCEL) {
                ci.cancel();
            }
            if (result.getType() == ReplacementEventResult.Type.REPLACE) {
                ci.cancel();
                newMessage = result.getValue();
            }
        }

        if (newMessage != null) {
            ClientPacketListener handler = (ClientPacketListener) (Object) this;
            this.sending = true;
            handler.sendChat(newMessage);
            this.sending = false;
        }
    }

}
