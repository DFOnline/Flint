package dev.dfonline.flint.feature.core;

public interface FeatureTrait {

    /**
     * @return Whether the feature is enabled or not,
     * this should be controllable by the user via config or other means.
     * @implNote This is not whether the feature is active,
     * but whether it is enabled, disabled features don't get their feature traits' methods called.
     */
    default boolean isEnabled() {
        return true;
    }

    /**
     * @return Whether the feature should always be enabled,
     * regardless of whether the user is on DiamondFire or not.
     */
    default boolean alwaysOn() {
        return false;
    }

}
