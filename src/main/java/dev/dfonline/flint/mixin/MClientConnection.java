package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.ChatListeningFeature;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.util.result.EventResult;
import dev.dfonline.flint.util.result.ReplacementEventResult;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class MClientConnection {

    @Inject(method = "genericsFtw", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        if (packet instanceof ClientboundSystemChatPacket(Component content, boolean overlay)) {
            Component newMessage = null;

            for (FeatureTrait feature : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.CHAT_LISTENING)) {
                ReplacementEventResult<Component> result = ((ChatListeningFeature) feature).onChatMessage(content, overlay);

                if (result.getType() == ReplacementEventResult.Type.CANCEL) {
                    ci.cancel();
                }

                if (result.getType() == ReplacementEventResult.Type.REPLACE) {
                    newMessage = result.getValue();
                }
            }

            if (newMessage != null) {
                ci.cancel();
                Flint.getUser().getPlayer().sendSystemMessage(newMessage);
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
