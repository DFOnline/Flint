package dev.dfonline.flint.feature.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.templates.Template;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemStack;

import static net.minecraft.text.Text.literal;

public class TestingTemplateCommand implements CommandFeature {
    @Override
    public String commandName() {
        return "template";
    }

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess) {
        return cmd.executes(this::run);
    }

    private int run(CommandContext<FabricClientCommandSource> context) {
        var player = Flint.getUser().getPlayer();

        ItemStack mainHandItem = player.getMainHandStack();
        Template template = Template.fromItem(mainHandItem);

        if (template != null) {
            template.printToChat();

            Flint.getUser().getPlayer().sendMessage(literal("JSONified: "), false);
            Flint.getUser().getPlayer().sendMessage(literal(template.toReadableJson().toString()), false);

            Flint.getUser().getPlayer().giveItemStack(template.toItem());
        }

        return 0;
    }
}
