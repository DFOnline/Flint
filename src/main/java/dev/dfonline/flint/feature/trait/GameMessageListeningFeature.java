package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.util.result.Result;
import net.minecraft.text.Text;

public interface GameMessageListeningFeature extends FeatureTrait {

    Result onGameMessage(Text text, boolean actionbar);

}
