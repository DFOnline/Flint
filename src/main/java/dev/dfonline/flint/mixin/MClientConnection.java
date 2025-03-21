package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.Result;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MClientConnection {

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            var result = ((PacketListeningFeature) feature).onReceivePacket(packet);

            if (result == Result.CANCEL) {
                ci.cancel();
            }
        });
    }

}
