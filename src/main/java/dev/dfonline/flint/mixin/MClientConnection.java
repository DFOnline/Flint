package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.GameMessageListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MClientConnection {

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (packet instanceof GameMessageS2CPacket(Text content, boolean overlay)) {
            boolean shouldReturn = false;
            for (var feature : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.GAME_MESSAGE_LISTENING)) {
                var result = ((GameMessageListeningFeature) feature).onGameMessage(content, overlay);

                if (result == EventResult.CANCEL) {
                    shouldReturn = true;
                }
            }

            if (shouldReturn) {
                ci.cancel();
                return;
            }
        }

        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            var result = ((PacketListeningFeature) feature).onReceivePacket(packet);

            if (result == EventResult.CANCEL) {
                ci.cancel();
            }
        });
    }

}
