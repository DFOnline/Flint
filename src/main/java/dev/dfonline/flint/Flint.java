package dev.dfonline.flint;

import dev.dfonline.flint.feature.FeatureManager;
import dev.dfonline.flint.feature.impl.CommandSender;
import dev.dfonline.flint.feature.impl.FlintCommandFeature;
import dev.dfonline.flint.feature.impl.LocateFeature;
import dev.dfonline.flint.feature.impl.ModeTrackerFeature;
import dev.dfonline.flint.feature.impl.PacketLoggerFeature;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.RenderedFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.util.Logger;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.minecraft.client.MinecraftClient;

public class Flint implements ClientModInitializer {

    public static final String MOD_ID = "flint";
    public static final String MOD_NAME = "Flint";
    public static final FeatureManager FEATURE_MANAGER = new FeatureManager();
    public static final MinecraftAudiences AUDIENCE = MinecraftClientAudiences.builder().build();

    private static final Logger LOGGER = Logger.of(Flint.class);
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static final User user = new User();

    public static MinecraftClient getClient() {
        return CLIENT;
    }

    public static User getUser() {
        return user;
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Sparking it up");

        // FlintAPI.setDebugging(true);
        FlintAPI.confirmLocationWithLocate();

        FEATURE_MANAGER.registerAll(
                // Debug
                new PacketLoggerFeature(),

                // Systems
                new CommandSender(),
                new LocateFeature(),

                // Functionality
                new ModeTrackerFeature(),
                new FlintCommandFeature()
        );

        // Ticking features.
        ClientTickEvents.START_CLIENT_TICK.register(client ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.TICKED).forEach(feature ->
                        ((TickedFeature) feature).tick()
                )
        );

        // Command features.
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.COMMAND).forEach(feature ->
                        ((CommandFeature) feature).onEnable(dispatcher, registryAccess)
                )
        );

        // Rendered features.
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.RENDERED).forEach(feature ->
                        ((RenderedFeature) feature).render(drawContext, renderTickCounter)
                )
        );

    }

}
