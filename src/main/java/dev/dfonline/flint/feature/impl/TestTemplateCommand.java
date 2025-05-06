package dev.dfonline.flint.feature.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.templates.Template;
import dev.dfonline.flint.templates.argument.ExpressionArgument;
import dev.dfonline.flint.templates.builders.ArgBuilder;
import dev.dfonline.flint.templates.builders.CodeBuilder;
import dev.dfonline.flint.templates.codeblock.SetVariable;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public class TestTemplateCommand implements CommandFeature {
    @Override
    public String commandName() {
        return "testing";
    }

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess) {
        return cmd.executes(this::run);
    }

    private int run(CommandContext<FabricClientCommandSource> context) {
        Template template = new Template();
        template.setName("Name");
        template.setAuthor("Author");
        template.setBlocks(
            CodeBuilder
                .create()
                .add(new SetVariable("=")
                         .setArguments(ArgBuilder
                                           .create()
                                           .add(new ExpressionArgument(0, "heylo"))
                                           .build()))
                .build()
        );

        Flint.getUser().getPlayer().giveItemStack(template.toItem());

        return 0;
    }
}
