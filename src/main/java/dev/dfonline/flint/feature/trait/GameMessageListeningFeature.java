package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.text.Text;

public interface GameMessageListeningFeature extends FeatureTrait {

    EventResult onGameMessage(Text text, boolean actionbar);

}
