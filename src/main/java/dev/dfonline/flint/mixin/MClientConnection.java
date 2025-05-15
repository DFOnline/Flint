package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.ChatListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.util.result.EventResult;
import dev.dfonline.flint.util.result.ReplacementEventResult;
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
            Text newMessage = null;

            for (FeatureTrait feature : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.CHAT_LISTENING)) {
                ReplacementEventResult<Text> result = ((ChatListeningFeature) feature).onChatMessage(content, overlay);

                if (result.getType() == ReplacementEventResult.Type.CANCEL) {
                    shouldReturn = true;
                }

                if (result.getType() == ReplacementEventResult.Type.REPLACE) {
                    newMessage = result.getValue();
                }
            }

            if (shouldReturn) {
                ci.cancel();
                return;
            }

            if (newMessage != null) {
                ci.cancel();
                Flint.getUser().getPlayer().sendMessage(newMessage, false);
                return;
            }
        }

        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            EventResult result = ((PacketListeningFeature) feature).onReceivePacket(packet);

            if (result == EventResult.CANCEL) {
                ci.cancel();
            }
        });
    }

}
