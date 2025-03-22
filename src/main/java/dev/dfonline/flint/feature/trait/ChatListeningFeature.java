package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.minecraft.text.Text;

public interface ChatListeningFeature extends FeatureTrait {

    EventResult onGameMessage(Text text, boolean actionbar);

}
