package dev.dfonline.flint;

import dev.dfonline.flint.feature.trait.FeatureTrait;

public final class FlintAPI {

    private static boolean confirmLocationWithLocate = false;
    private static boolean debugging = false;

    private FlintAPI() {
    }

    /**
     * Lets Flint know that it should run /locate after a suspected mode change.
     *
     * <p>
     * This is useful when you want to be 100% sure about the player's mode, such as when done by outside forces.
     * </p>
     *
     * <p>
     * Enabling this also gives access to the player's plot, something that is not available in the default mode change detection.
     * </p>
     *
     * @implNote This can only be enabled, not disabled, make sure to run this on your mod's initialization method.
     */
    public static void confirmLocationWithLocate() {
        confirmLocationWithLocate = true;
    }

    /**
     * @return Whether Flint should confirm the player's location with /locate after a suspected mode change.
     */
    public static boolean shouldConfirmLocationWithLocate() {
        return confirmLocationWithLocate;
    }

    /**
     * Set whether Flint should print debug messages.
     *
     * @param debugging Whether Flint should print debug messages.
     */
    public static void setDebugging(boolean debugging) {
        FlintAPI.debugging = debugging;
    }

    /**
     * @return Whether Flint should print debug messages.
     */
    public static boolean isDebugging() {
        return debugging;
    }

    /**
     * Register a feature with Flint, call this on your mod's initialization method.
     *
     * @param feature The feature to register.
     */
    public static void registerFeature(FeatureTrait feature) {
        Flint.FEATURE_MANAGER.register(feature);
    }

    /**
     * Register multiple features with Flint, call this on your mod's initialization method.
     *
     * @param features The features to register.
     */
    public static void registerFeatures(FeatureTrait features) {
        Flint.FEATURE_MANAGER.registerAll(features);
    }

}
