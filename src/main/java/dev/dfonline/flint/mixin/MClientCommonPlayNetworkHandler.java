package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.PacketListeningFeature;
import dev.dfonline.flint.feature.trait.UserCommandListeningFeature;
import dev.dfonline.flint.util.result.ReplaceEventResult;
import dev.dfonline.flint.util.result.Result;
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
            for (var trait : Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.USER_COMMAND_LISTENING)) {
                var result = ((UserCommandListeningFeature) trait).sendCommand(command);
                if (result.getType() == ReplaceEventResult.Type.CANCEL) {
                    ci.cancel();
                }
                if (result.getType() == ReplaceEventResult.Type.REPLACE) {
                    ci.cancel();
                    newCommand = result.getValue();
                }
            }

            if (newCommand != null && Flint.getClient().getNetworkHandler() != null) {
                this.sendingModifiedCommand = true;
                Flint.getClient().getNetworkHandler().sendPacket(new CommandExecutionC2SPacket(newCommand));
                this.sendingModifiedCommand = false;
                return;
            }
        }

        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.PACKET_LISTENING).forEach(feature -> {
            var result = ((PacketListeningFeature) feature).onSendPacket(packet);

            if (result == Result.CANCEL) {
                ci.cancel();
            }
        });
    }

}
