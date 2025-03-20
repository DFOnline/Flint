package dev.dfonline.flint.event;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTrait;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.ArrayList;
import java.util.List;

public interface FeatureRegistrationCallback {
    Event<FeatureRegistrationCallback> EVENT = EventFactory.createArrayBacked(FeatureRegistrationCallback.class,
            (listeners) -> () -> {
                for (FeatureRegistrationCallback listener : listeners) {
                    List<FeatureTrait> features = listener.getFeatures();
                    for (FeatureTrait feature : features) {
                        Flint.FEATURE_MANAGER.register(feature);
                    }
                }
                return null;
            });

    List<FeatureTrait> getFeatures();
}