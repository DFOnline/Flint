package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import dev.dfonline.flint.util.result.EventResult;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelTerrainRenderContext;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.world.phys.HitResult;

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
    default void worldRenderAfterBlockOutlineExtraction(LevelExtractionContext context, HitResult hit) {
    }

    /**
     * Called after all render states are extracted, before any is drawn.
     *
     * @param context The world extraction context
     */
    default void worldRenderEndExtraction(LevelExtractionContext context) {
    }

    /**
     * Called after all chunks to be rendered are uploaded to GPU,
     * before any chunks are drawn to the framebuffer.
     *
     * @param context The world terrain render context
     */
    default void worldRenderStartMain(LevelTerrainRenderContext context) {
    }

    /**
     * Called after {@linkplain net.minecraft.client.renderer.chunk.ChunkSectionLayerGroup#OPAQUE opaque terrain} is drawn
     * and entity, block entity, and particle submit nodes are collected, before their geometry is drawn.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeEntities(LevelRenderContext context) {
    }

    /**
     * Called after entities and block entities are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderAfterEntities(LevelRenderContext context) {
    }

    /**
     * Called after entities, block breaking, and most non-translucent objects are drawn to the framebuffer,
     * before vanilla debug renderers and translucency are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeDebugRender(LevelRenderContext context) {
    }

    /**
     * Called after entities and block entities are drawn to the framebuffer,
     * before translucent terrain is drawn to the framebuffer,
     * and before translucency combine has happened in fabulous mode.
     *
     * @param context The world render context
     */
    default void worldRenderBeforeTranslucent(LevelRenderContext context) {
    }

    /**
     * Called after block outline render checks are made
     * and before the default block outline is drawn to the framebuffer.
     *
     * @param context            The world render context
     * @param outlineRenderState The outline render state
     * @return The event result
     */
    default EventResult worldRenderBeforeBlockOutline(LevelRenderContext context, BlockOutlineRenderState outlineRenderState) {
        return EventResult.PASS;
    }

    /**
     * Called at the end of the main render pass, after entities, block entities,
     * terrain, and translucent terrain are drawn to the framebuffer,
     * before particles, clouds, weather, and late debug are drawn to the framebuffer.
     *
     * @param context The world render context
     */
    default void worldRenderEndMain(LevelRenderContext context) {
    }

}
