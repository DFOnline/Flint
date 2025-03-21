package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.UserMessageListeningFeature;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ClientPlayNetworkHandler.class)
public class MClientPlayNetworkHandler {
    @Unique
    private boolean sending = false;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void sendChatMessage(String message, CallbackInfo ci) {
        if (sending) {
            return;
        }

        Optional<String> newMessage = Optional.empty();

        for (var trait : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.USER_SEND_MESSAGE)) {
            var result = ((UserMessageListeningFeature) trait).sendMessage(message);
            if (result.getType() == UserMessageListeningFeature.Result.Type.CANCEL) {
                ci.cancel();
            }
            if (result.getType() == UserMessageListeningFeature.Result.Type.REPLACE) {
                ci.cancel();
                newMessage = result.getMessage();
            }
        }

        if (newMessage.isPresent()) {
            ClientPlayNetworkHandler handler = (ClientPlayNetworkHandler) (Object) this;
            sending = true;
            handler.sendChatMessage(newMessage.get());
            sending = false;
        }
    }
}
