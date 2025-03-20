package dev.dfonline.flint.feature.trait;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public interface RenderedFeature extends FeatureTrait {

    void render(DrawContext context, RenderTickCounter tickCounter);

}
