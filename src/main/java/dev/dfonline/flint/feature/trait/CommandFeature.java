package dev.dfonline.flint.feature.trait;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.dfonline.flint.feature.core.FeatureTrait;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

import java.util.Set;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

/**
 * A feature that registers client-side commands.
 */
public interface CommandFeature extends FeatureTrait {

    /**
     * Returns a set of command aliases.
     *
     * @return A set of command aliases
     */
    default Set<String> aliases() {
        return Set.of();
    }

    /**
     * Returns the name of the command.
     *
     * @return The command name
     */
    String commandName();

    /**
     * Creates the command.
     *
     * @param cmd            The literal argument builder for the command
     * @param registryAccess Registry access
     * @return The command with its functionality added
     */
    LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess);

    /**
     * Registers the command and its aliases.
     *
     * @param dispatcher The command dispatcher
     * @param registryAccess Registry access
     */
    default void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(this.createCommand(literal(this.commandName()), registryAccess));

        for (var alias : this.aliases()) {
            dispatcher.register(this.createCommand(literal(alias), registryAccess));
        }
    }

}
