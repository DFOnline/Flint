package dev.dfonline.flint;

import dev.dfonline.flint.feature.FeatureManager;
import dev.dfonline.flint.feature.impl.*;
import dev.dfonline.flint.feature.trait.*;
import dev.dfonline.flint.feature.trait.results.Result;
import dev.dfonline.flint.util.Logger;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
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

        this.registerEventCallbacks();
    }

    private void registerEventCallbacks() {
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

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.TOOLTIP_RENDER).forEach(feature ->
                    ((TooltipRenderFeature) feature).tooltipRender(itemStack, tooltipContext, tooltipType, list));
        });

        WorldRenderEvents.LAST.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(
                    feature -> ((WorldRenderFeature) feature).worldRenderLast(worldRenderContext)
            );
        });

        WorldRenderEvents.END.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(
                    feature -> ((WorldRenderFeature) feature).worldRenderEnd(worldRenderContext)
            );
        });

        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((worldRenderContext, hitResult) -> {
            boolean shouldRender = true;
            for (var feature : FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER)) {
                if (((WorldRenderFeature) feature).worldRenderBeforeBlockOutline(worldRenderContext, hitResult) == Result.CANCEL) {
                    shouldRender = false;
                }
            }

            return shouldRender;
        });

        WorldRenderEvents.BEFORE_ENTITIES.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                ((WorldRenderFeature) feature).worldRenderBeforeEntities(worldRenderContext);
            });
        });

        WorldRenderEvents.AFTER_ENTITIES.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                ((WorldRenderFeature) feature).worldRenderAfterEntities(worldRenderContext);
            });
        });

        WorldRenderEvents.AFTER_SETUP.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                        ((WorldRenderFeature) feature).worldRenderAfterSetup(worldRenderContext);
            });
        });

        WorldRenderEvents.AFTER_TRANSLUCENT.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                ((WorldRenderFeature) feature).worldRenderAfterTranslucent(worldRenderContext);
            });
        });

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                ((WorldRenderFeature) feature).worldRenderBeforeDebugRender(worldRenderContext);
            });
        });

        WorldRenderEvents.BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
            boolean shouldRender = true;
            for (var feature : FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER)) {
                if (((WorldRenderFeature) feature).worldRenderBlockOutline(worldRenderContext, blockOutlineContext) == Result.CANCEL) {
                    shouldRender = false;
                }
            }

            return shouldRender;
        });

        WorldRenderEvents.START.register(worldRenderContext -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature -> {
                ((WorldRenderFeature) feature).worldRenderStart(worldRenderContext);
            });
        });
    }

}
