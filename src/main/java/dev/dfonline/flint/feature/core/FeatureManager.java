package dev.dfonline.flint.feature.core;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.hypercube.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for registering and getting features based on their traits.
 * Features are registered by the trait interfaces they implement, and can be gotten based on that.
 */
public class FeatureManager {

    /**
     * Array of feature lists, indexed by trait ordinal values.
     */
    private final List<FeatureTrait>[] featureLists;

    /**
     * Creates a new FeatureManager with arrays initialized for all trait types.
     */
    @SuppressWarnings("unchecked")
    public FeatureManager() {
        this.featureLists = new List[FeatureTraitType.values().length];
        for (int i = 0; i < this.featureLists.length; i++) {
            this.featureLists[i] = new ArrayList<>();
        }
    }

    /**
     * Registers a feature by checking which traits it implements and adding it
     * to the corresponding lists.
     *
     * @param feature The feature to register
     */
    public void register(FeatureTrait feature) {
        for (FeatureTraitType trait : FeatureTraitType.values()) {
            if (trait.getFeatureClass().isInstance(feature)) {
                this.featureLists[trait.getIndex()].add(feature);
            }
        }
    }

    /**
     * Registers multiple features at once.
     *
     * @param features Array of features to register
     */
    public void registerAll(FeatureTrait... features) {
        for (FeatureTrait feature : features) {
            this.register(feature);
        }
    }

    /**
     * Gets all features that implement the specified trait.
     *
     * @param trait               The trait to look up
     * @param onlyEnabledFeatures Whether to only return enabled features
     * @return List of features implementing the specified trait
     */
    public List<FeatureTrait> getByTrait(FeatureTraitType trait, boolean onlyEnabledFeatures) {

        List<FeatureTrait> traits = this.featureLists[trait.getIndex()];

        // When the player is not on DiamondFire, only return always-on features
        if (Flint.getUser().getMode() == Mode.NONE) {
            traits = traits.stream().filter(FeatureTrait::alwaysOn).toList();
        }

        if (onlyEnabledFeatures) {
            return traits.stream().filter(FeatureTrait::isEnabled).toList();
        } else {
            return traits;
        }
    }

    /**
     * Gets all features that implement the specified trait.
     *
     * @param trait The trait to look up
     * @return List of features implementing the specified trait
     */
    public List<FeatureTrait> getByTrait(FeatureTraitType trait) {
        return this.getByTrait(trait, true);
    }


}
