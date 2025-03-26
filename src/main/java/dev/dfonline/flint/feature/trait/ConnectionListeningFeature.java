package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;

/**
 * A feature that listens to server joins and leaves.
 */
public interface ConnectionListeningFeature extends FeatureTrait {

    default void onJoin() {
    }

    default void onDisconnect() {
    }

}
