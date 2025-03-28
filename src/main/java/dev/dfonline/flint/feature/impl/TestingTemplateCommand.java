package dev.dfonline.flint.feature.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.FlintAPI;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.templates.Arguments;
import dev.dfonline.flint.templates.CodeBlocks;
import dev.dfonline.flint.templates.Template;
import dev.dfonline.flint.templates.argument.NumberArgument;
import dev.dfonline.flint.templates.argument.StringArgument;
import dev.dfonline.flint.templates.argument.TextArgument;
import dev.dfonline.flint.templates.argument.VectorArgument;
import dev.dfonline.flint.templates.builders.ArgBuilder;
import dev.dfonline.flint.templates.builders.CodeBuilder;
import dev.dfonline.flint.templates.codeblock.PlayerAction;
import dev.dfonline.flint.templates.codeblock.target.PlayerTarget;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class TestingTemplateCommand implements CommandFeature {
    @Override
    public String commandName() {
        return "template";
    }

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess) {
        return cmd.then(
            literal("get").executes(this::run)
        ).then(
            literal("read").executes(this::read)
        );
    }

    private int read(CommandContext<FabricClientCommandSource> context) {
        var item = context.getSource().getPlayer().getMainHandStack();
        FlintAPI.setDebugging(true);
        Template template = Template.fromItem(item);
        return 1;
    }

    private int run(CommandContext<FabricClientCommandSource> context) {
        var player = context.getSource().getPlayer();

        var template = new Template();
        template.setAuthor("zBinFinn");
        template.setMaterial(Items.STONE);
        template.setName("&azBinFinn's Incredible Template");

        CodeBlocks blocks =
            CodeBuilder.create()
                .add(new PlayerAction("SendMessage", PlayerTarget.ALL)
                         .setArguments(
                             ArgBuilder.create()
                                 .add(new TextArgument(1, "<green>Hello people"))
                                 .add(new StringArgument(3, "This is a string!!"))
                                 .add(new NumberArgument(8, 5))
                                 .add(new VectorArgument(1, 5, 5, 5))
                                 .build()
                         ))
                .build();

        template.setBlocks(blocks);
        player.giveItemStack(template.toItem());

        return 0;
    }
}
