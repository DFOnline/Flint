package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.trait.results.Result;
import net.minecraft.text.Text;

public interface GameMessageListeningFeature extends FeatureTrait {
    Result onGameMessage(Text text, boolean actionbar);
}
