package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.UserCommandListeningFeature;
import dev.dfonline.flint.util.result.EventResult;
import dev.dfonline.flint.util.result.ReplacementEventResult;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class MClientCommonPlayNetworkHandler {

    @Unique
    private boolean sendingModifiedCommand = false;

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    private void sendPacket(Packet<?> packet, CallbackInfo ci) {
        if (!this.sendingModifiedCommand && packet instanceof CommandExecutionC2SPacket(String command)) {
            String newCommand = null;
            boolean shouldReturn = false;
            for (FeatureTrait trait : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.USER_COMMAND_LISTENING)) {
                ReplacementEventResult<String> result = ((UserCommandListeningFeature) trait).sendCommand(command);
                if (result.getType() == ReplacementEventResult.Type.CANCEL) {
                    shouldReturn = true;
                }
                if (result.getType() == ReplacementEventResult.Type.REPLACE) {
                    ci.cancel();
                    newCommand = result.getValue();
                }
            }

            if (shouldReturn) {
                ci.cancel();
                return;
            }

            if (newCommand != null && Flint.getClient().getNetworkHandler() != null) {
                this.sendingModifiedCommand = true;
                Flint.getClient().getNetworkHandler().sendPacket(new CommandExecutionC2SPacket(newCommand));
                this.sendingModifiedCommand = false;
            }
        }

        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            EventResult result = ((PacketListeningFeature) feature).onSendPacket(packet);

            if (result == EventResult.CANCEL) {
                ci.cancel();
            }
        });
    }

}
