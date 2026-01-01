package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldTerrainRenderContext;
import net.minecraft.client.render.state.OutlineRenderState;
import net.minecraft.util.hit.HitResult;

/**
 * A feature that receives world rendering events.
 */
public interface WorldRenderFeature extends FeatureTrait {

    /**
     * Called after the block outline render state is extracted, before it is drawn.
     *
     * @param context The world extraction context
     * @param hit     The hit result of the block being outlined
     */
    default void worldRenderAfterBlockOutlineExtraction(WorldExtractionContext context, HitResult hit) {
    }

    /**
     * Called after all render states are extracted, before any is drawn.
     *
     * @param context The world extraction context
     */
    default void worldRenderEndExtraction(WorldExtractionContext context) {
    }

    /**
     * Called after all chunks to be rendered are uploaded to GPU,
     * before any chunks are drawn to the framebuffer.
     *
     * @param context The world terrain render context
     */
    default void worldRenderStartMain(WorldTerrainRenderContext context) {
    }

    /**
     * Called after the {@link net.minecraft.client.render.BlockRenderLayer#SOLID SOLID}, {@link net.minecraft.client.render.BlockRenderLayer#CUTOUT CUTOUT},
     * and {@link net.minecraft.client.render.BlockRenderLayer#CUTOUT CUTOUT_MIPPED} terrain layers are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeEntities(WorldRenderContext context) {
    }

    /**
     * Called after entities and block entities are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderAfterEntities(WorldRenderContext context) {
    }

    /**
     * Called after entities, block breaking, and most non-translucent objects are drawn to the framebuffer,
     * before vanilla debug renderers and translucency are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeDebugRender(WorldRenderContext context) {
    }

    /**
     * Called after entities and block entities are drawn to the framebuffer,
     * before translucent terrain is drawn to the framebuffer,
     * and before translucency combine has happened in fabulous mode.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeTranslucent(WorldRenderContext context) {
    }

    /**
     * Called after block outline render checks are made
     * and before the default block outline is drawn to the framebuffer.
     *
     * @param context            The world render context
     * @param outlineRenderState The outline render state
     * @return The event result
     */
    default EventResult worldRenderBeforeBlockOutline(WorldRenderContext context, OutlineRenderState outlineRenderState) {
        return EventResult.PASS;
    }

    /**
     * Called at the end of the main render pass, after entities, block entities,
     * terrain, and translucent terrain are drawn to the framebuffer,
     * before particles, clouds, weather, and late debug are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderEndMain(WorldRenderContext context) {
    }

}
