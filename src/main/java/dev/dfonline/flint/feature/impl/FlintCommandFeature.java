package dev.dfonline.flint.feature.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.hypercube.Plot;
import dev.dfonline.flint.util.ComponentUtil;
import dev.dfonline.flint.util.FlintSound;
import dev.dfonline.flint.util.message.impl.CompoundMessage;
import dev.dfonline.flint.util.message.impl.SoundMessage;
import dev.dfonline.flint.util.message.impl.prefix.ErrorMessage;
import dev.dfonline.flint.util.message.impl.prefix.InfoMessage;
import dev.dfonline.flint.util.message.impl.prefix.SuccessMessage;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.kyori.adventure.text.Component;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.sound.SoundEvents;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FlintCommandFeature implements CommandFeature {

    @Override
    public String commandName() {
        return "flint";
    }

    @Override
    public LiteralArgumentBuilder<FabricClientCommandSource> createCommand(LiteralArgumentBuilder<FabricClientCommandSource> cmd, CommandRegistryAccess registryAccess) {
        return cmd.executes(context -> {
            Flint.getUser().sendMessage(new CompoundMessage(
                    new SuccessMessage("flint.command.flint"),
                    new SoundMessage(FlintSound.builder()
                            .setSound(SoundEvents.ENTITY_VILLAGER_YES)
                            .setPitch(2.0F)
                            .build()
                    )
            ));
            return 1;
        }).then(literal("mode")
                .executes(context -> {
                    Plot plot = Flint.getUser().getPlot();

                    String plotString = "null";
                    if (plot != null) {
                        plotString = plot.toReadableString();
                    }
                    String nodeString = "null";
                    if (Flint.getUser().getNode() != null) {
                        nodeString = Flint.getUser().getNode().getName();
                    }

                    Flint.getUser().sendMessage(new CompoundMessage(new InfoMessage("flint.command.flint.mode", Component.text(Flint.getUser().getMode().getName()), Component.text(plotString), Component.text(nodeString))));
                    return 1;
                })
        ).then(literal("clear_queue")
                .executes(context -> {
                    if (CommandSenderFeature.queueSize() > 0) {
                        CommandSenderFeature.clearQueue();
                        Flint.getUser().sendMessage(new SuccessMessage("flint.command.flint.clear_queue.success"));
                    } else {
                        Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.clear_queue.empty"));
                    }
                    return 1;
                })
        ).then(literal("locate_test")
                .executes(context -> {
                    debugLocate(Flint.getUser().getPlayer().getNameForScoreboard());
                    return 1;
                })
                .then(argument("player", StringArgumentType.greedyString())
                        .executes(context -> {
                            debugLocate(StringArgumentType.getString(context, "player"));
                            return 1;
                        })
                )
        ).then(literal("action_dump")
                .executes(context -> {
                    GetActionDumpFeature.getActionDump(ComponentUtil.ColorMode.MINI_MESSAGE, false);
                    return 1;
                })
                .then(argument("color_mode", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            for (ComponentUtil.ColorMode colorMode : ComponentUtil.ColorMode.values()) {
                                builder.suggest(colorMode.name().toLowerCase());
                            }
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            ComponentUtil.ColorMode colorMode = ComponentUtil.ColorMode.valueOf(StringArgumentType.getString(context, "color_mode").toUpperCase());
                            GetActionDumpFeature.getActionDump(colorMode, false);
                            return 1;
                        })
                        .then(literal("force")
                                .executes(context -> {
                                    ComponentUtil.ColorMode colorMode = ComponentUtil.ColorMode.valueOf(StringArgumentType.getString(context, "color_mode").toUpperCase());
                                    GetActionDumpFeature.getActionDump(colorMode, true);
                                    return 1;
                                })
                        )
                )
                .then(literal("force")
                        .executes(context -> {
                            GetActionDumpFeature.getActionDump(ComponentUtil.ColorMode.MINI_MESSAGE, true);
                            return 1;
                        })
                )
        );
    }

    private static void debugLocate(String player) {
        LocateFeature.requestLocate(player).thenAccept(locate -> {

            Plot plot = locate.plot();
            String plotString = "null";
            if (plot != null) {
                plotString = plot.toReadableString();
            }

            Flint.getUser().sendMessage(new SuccessMessage("flint.command.flint.locate_test.success", Component.text(locate.player()), Component.text(locate.mode().getName()), Component.text(plotString), Component.text(locate.node().getName())));

        }).exceptionally(throwable -> {
            Flint.getUser().sendMessage(new ErrorMessage("flint.command.flint.locate_test.fail", Component.text(throwable.getMessage())));
            return null;
        });
    }

}
