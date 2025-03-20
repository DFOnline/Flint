package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class MClientCommonPlayNetworkHandler {

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void sendPacket(Packet<?> packet, CallbackInfo ci) {
        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            var result = ((PacketListeningFeature) feature).onSendPacket(packet);

            if (result == PacketListeningFeature.PacketResult.CANCEL) {
                ci.cancel();
            }
        });
    }

}