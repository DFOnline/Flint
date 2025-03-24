package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import net.minecraft.client.world.ClientWorld;

public interface WorldChangeListeningFeature extends FeatureTrait {

    void onWorldChange(ClientWorld world);

}
