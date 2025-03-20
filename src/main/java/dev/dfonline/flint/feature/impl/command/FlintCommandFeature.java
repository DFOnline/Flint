package dev.dfonline.flint.feature.impl.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.util.FSound;
import dev.dfonline.flint.util.message.impl.CompoundMessage;
import dev.dfonline.flint.util.message.impl.SoundMessage;
import dev.dfonline.flint.util.message.impl.prefix.SuccessMessage;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.sound.SoundEvents;

public class FlintCommandFeature implements CommandFeature {

    @Override
    public String commandName() {
        return "flint";
    }

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess) {
        return cmd.executes(context ->{
            Flint.getUser().sendMessage(new CompoundMessage(
                    new SuccessMessage("flint.command.flint"),
                    new SoundMessage(FSound.builder()
                            .soundEvent(SoundEvents.ENTITY_VILLAGER_YES)
                            .pitch(2.0F)
                            .build()
                    )
            ));
            return 1;
        });
    }

}
