package dev.dfonline.flint.feature.trait;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

import java.util.List;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public interface CommandFeature extends FeatureTrait {

    default List<String> aliases() {
        return List.of();
    }

    String commandName();

    LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess);

    default void onEnable(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(this.createCommand(literal(this.commandName()), registryAccess));

        for (var alias : this.aliases()) {
            dispatcher.register(this.createCommand(literal(alias), registryAccess));
        }
    }

}
