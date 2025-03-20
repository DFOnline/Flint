package dev.dfonline.flint;

import dev.dfonline.flint.event.FeatureRegistrationCallback;
import dev.dfonline.flint.feature.FeatureManager;
import dev.dfonline.flint.feature.impl.ModeTrackerFeature;
import dev.dfonline.flint.feature.impl.command.FlintCommandFeature;
import dev.dfonline.flint.feature.trait.CommandFeature;
import dev.dfonline.flint.feature.trait.FeatureTrait;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.TickableFeature;
import dev.dfonline.flint.util.Logger;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class Flint implements ClientModInitializer {

    public static final String MOD_ID = "flint";
    public static final String MOD_NAME = "Flint";
    private static final Logger LOGGER = Logger.of(Flint.class);
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    public static final FeatureManager FEATURE_MANAGER = new FeatureManager();
    private static final User user = new User();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Sparking it up");

        // Register our features for when the event is fired.
        FeatureRegistrationCallback.EVENT.register(() -> List.of(
                new FlintCommandFeature(),
                new ModeTrackerFeature()
        ));

        // Let listeners register their features.
        FeatureRegistrationCallback.EVENT.invoker().getFeatures();

        // Let ticking features tick.
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.TICKABLE).forEach(feature -> {
                ((TickableFeature) feature).tick();
            });
        });

        // Let command features register their commands.
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            FEATURE_MANAGER.getByTrait(FeatureTraitType.COMMAND).forEach(feature -> {
                ((CommandFeature) feature).onEnable(dispatcher, registryAccess);
            });
        });

    }

    public static MinecraftClient getClient() {
        return CLIENT;
    }

    public static User getUser() {
        return user;
    }

}
