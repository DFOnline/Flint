package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

/**
 * A feature that renders to the screen.
 *
 * @deprecated Use {@link HudElementRegistry} and {@link HudElement} instead.
 */
@Deprecated
public interface RenderedFeature extends FeatureTrait {

    /**
     * Called each frame to render the feature.
     *
     * @param context     The drawing context
     * @param tickCounter The tick counter
     */
    void render(GuiGraphicsExtractor context, DeltaTracker tickCounter);

}
