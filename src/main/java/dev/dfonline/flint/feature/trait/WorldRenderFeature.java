package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.util.hit.HitResult;

/**
 * A feature that receives world rendering events.
 */
public interface WorldRenderFeature extends FeatureTrait {

    /**
     * Called at the very end of the world rendering process.
     *
     * @param context The world render context
     */
    default void worldRenderLast(WorldRenderContext context) {
    }

    /**
     * Called when world rendering has ended.
     *
     * @param context The world render context
     */
    default void worldRenderEnd(WorldRenderContext context) {
    }

    /**
     * Called before the block outline is rendered.
     *
     * @param context The world render context
     * @param hit     The hit result for the block being outlined
     * @return The event result
     */
    default EventResult worldRenderBeforeBlockOutline(WorldRenderContext context, HitResult hit) {
        return EventResult.PASS;
    }

    /**
     * Called before entities are rendered in the world.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeEntities(WorldRenderContext context) {
    }

    /**
     * Called after entities are rendered in the world.
     *
     * @param context The world render context
     */
    default void worldRenderAfterEntities(WorldRenderContext context) {
    }

    /**
     * Called after the rendering setup is complete.
     *
     * @param context The world render context
     */
    default void worldRenderAfterSetup(WorldRenderContext context) {
    }

    /**
     * Called after translucent blocks are rendered.
     *
     * @param context The world render context
     */
    default void worldRenderAfterTranslucent(WorldRenderContext context) {
    }

    /**
     * Called before debug rendering occurs.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeDebugRender(WorldRenderContext context) {
    }

    /**
     * Called when a block outline is being rendered.
     *
     * @param context The world render context
     * @param blockOutlineContext Context specific to the block outline being rendered
     * @return The event result
     */
    default EventResult worldRenderBlockOutline(WorldRenderContext context, WorldRenderContext.BlockOutlineContext blockOutlineContext) {
        return EventResult.PASS;
    }

    /**
     * Called when world rendering begins.
     *
     * @param context The world render context
     */
    default void worldRenderStart(WorldRenderContext context) {
    }

}
