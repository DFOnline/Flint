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
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.kyori.adventure.platform.modcommon.MinecraftAudiences;
import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;
import net.minecraft.client.MinecraftClient;

import java.util.Arrays;

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
        System.out.println(ActionDump.get().toString());
        System.out.println(Arrays.toString(ActionDump.get().gameValues()));
        System.out.println(Arrays.toString(ActionDump.get().gameValueCategories()));
        System.exit(0);
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

        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.RENDERED).forEach(feature ->
                        ((RenderedFeature) feature).render(drawContext, renderTickCounter)
                )
        );

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.TOOLTIP_RENDER).forEach(feature ->
                        ((TooltipRenderFeature) feature).tooltipRender(itemStack, tooltipContext, tooltipType, list)
                )
        );

        WorldRenderEvents.AFTER_BLOCK_OUTLINE_EXTRACTION.register((context, hit) ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderAfterBlockOutlineExtraction(context, hit)
                )
        );

        WorldRenderEvents.END_EXTRACTION.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderEndExtraction(context)
                )
        );

        WorldRenderEvents.START_MAIN.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderStartMain(context)
                )
        );
        
        WorldRenderEvents.BEFORE_ENTITIES.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeEntities(context)
                )
        );
        
        WorldRenderEvents.AFTER_ENTITIES.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderAfterEntities(context)
                )
        );
        
        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeDebugRender(context)
                )
        );   
        
        WorldRenderEvents.BEFORE_TRANSLUCENT.register(context ->
                FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER).forEach(feature ->
                        ((WorldRenderFeature) feature).worldRenderBeforeTranslucent(context)
                )
        );

        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((context, outlineRenderState) -> {
            boolean shouldRender = true;
            for (FeatureTrait feature : FEATURE_MANAGER.getByTrait(FeatureTraitType.WORLD_RENDER)) {
                if (((WorldRenderFeature) feature).worldRenderBeforeBlockOutline(context, outlineRenderState) == EventResult.CANCEL) {
                    shouldRender = false;
                }
            }

            return shouldRender;
        });

        WorldRenderEvents.END_MAIN.register(context ->
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
