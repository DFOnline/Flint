package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

/**
 * A feature that renders to the screen.
 */
public interface RenderedFeature extends FeatureTrait {

    /**
     * Called each frame to render the feature.
     *
     * @param context     The drawing context
     * @param tickCounter The tick counter
     */
    void render(DrawContext context, RenderTickCounter tickCounter);

}
