package dev.dfonline.flint;

import dev.dfonline.flint.actiondump.ActionDump;
import dev.dfonline.flint.feature.core.FeatureManager;
import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.feature.core.FeatureTraitType;
import dev.dfonline.flint.feature.impl.CommandSenderFeature;
import dev.dfonline.flint.feature.impl.FlintCommandFeature;
import dev.dfonline.flint.feature.impl.GetActionDumpFeature;
import dev.dfonline.flint.feature.impl.LocateFeature;
import dev.dfonline.flint.feature.impl.ModeTrackerFeature;
import dev.dfonline.flint.feature.impl.PacketLoggerFeature;
import dev.dfonline.flint.feature.impl.StateDebugDisplayFeature;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.feature.trait.ConnectionListeningFeature;
import dev.dfonline.flint.feature.trait.RenderedFeature;
import dev.dfonline.flint.feature.trait.ShutdownFeature;
import dev.dfonline.flint.feature.trait.TickedFeature;
import dev.dfonline.flint.feature.trait.TooltipRenderFeature;
import dev.dfonline.flint.feature.trait.WorldRenderFeature;
import dev.dfonline.flint.util.FlintUpdate;
import dev.dfonline.flint.util.Logger;
import dev.dfonline.flint.util.result.EventResult;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.util.Arrays;

public class Flint implements ClientModInitializer {

    public static final String MOD_ID = "flint";
    public static final String MOD_NAME = "Flint";
    public static final FeatureManager FEATURE_MANAGER = new FeatureManager();
    public static final MinecraftAudiences AUDIENCE = MinecraftClientAudiences.builder().build();

    private static final Logger LOGGER = Logger.of(Flint.class);
    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final User user = new User();

    public static Minecraft getClient() {
        return CLIENT;
    }

    public static User getUser() {
        return user;
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Sparking it up");

//         FlintAPI.setDebugging(true);
//         FlintAPI.confirmLocationWithLocate();

        FlintUpdate.fetchLatestRelease();

        FEATURE_MANAGER.registerAll(
                // Debug
                new StateDebugDisplayFeature(),
                new PacketLoggerFeature(),

                // Systems
                new CommandSenderFeature(),
                new LocateFeature(),

                // Functionality
                new ModeTrackerFeature(),
                new GetActionDumpFeature(),
                new FlintCommandFeature()
        );

        this.registerEventCallbacks();
    }

    private void registerEventCallbacks() {
        ClientTickEvents.START_CLIENT_TICK.register(client ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.TICKED).forEach(feature ->
                        ((TickedFeature) feature).tick()
                )
        );

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.COMMAND, false).forEach(feature ->
                        ((CommandFeature) feature).register(dispatcher, registryAccess)
                )
        );

        HudElementRegistry.addLast(Identifier.fromNamespaceAndPath(MOD_ID, "features"), (drawContext, renderTickCounter) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.RENDERED).forEach(feature ->
                        ((RenderedFeature) feature).render(drawContext, renderTickCounter)
                )
        );

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.TOOLTIP_RENDER).forEach(feature ->
                        ((TooltipRenderFeature) feature).tooltipRender(itemStack, tooltipContext, tooltipType, list)
                )
        );

        LevelExtractionEvents.AFTER_BLOCK_OUTLINE_EXTRACTION.register((context, hit) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderAfterBlockOutlineExtraction(context, hit)
                )
        );

        LevelExtractionEvents.END_EXTRACTION.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderEndExtraction(context)
                )
        );

        LevelRenderEvents.START_MAIN.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderStartMain(context)
                )
        );
        
        LevelRenderEvents.COLLECT_SUBMITS.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeEntities(context)
                )
        );
        
        LevelRenderEvents.AFTER_SOLID_FEATURES.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderAfterEntities(context)
                )
        );
        
        LevelRenderEvents.BEFORE_GIZMOS.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeDebugRender(context)
                )
        );   
        
        LevelRenderEvents.BEFORE_TRANSLUCENT_TERRAIN.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeTranslucent(context)
                )
        );

        LevelRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, outlineRenderState) -> {
            boolean shouldRender = true;
            for (FeatureTrait feature : FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER)) {
                if (((WorldRenderFeature) feature).worldRenderBeforeBlockOutline(context, outlineRenderState) == EventResult.CANCEL) {
                    shouldRender = false;
                }
            }

            return shouldRender;
        });

        LevelRenderEvents.END_MAIN.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderEndMain(context)
                )
        );
        
        ClientLifecycleEvents.CLIENT_STOPPING.register(client ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.SHUTDOWN).forEach(feature ->
                        ((ShutdownFeature) feature).onShutdown()
                )
        );

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.CONNECTION_LISTENING).forEach(feature ->
                        ((ConnectionListeningFeature) feature).onDisconnect()
                )
        );

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.CONNECTION_LISTENING).forEach(feature ->
                        ((ConnectionListeningFeature) feature).onJoin()
                )
        );
    }

}
