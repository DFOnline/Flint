package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.util.result.Result;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.util.hit.HitResult;

public interface WorldRenderFeature extends FeatureTrait {

    default void worldRenderLast(WorldRenderContext context) {
    }

    default void worldRenderEnd(WorldRenderContext context) {
    }

    default Result worldRenderBeforeBlockOutline(WorldRenderContext context, HitResult hit) {
        return Result.PASS;
    }

    default void worldRenderBeforeEntities(WorldRenderContext context) {
    }

    default void worldRenderAfterEntities(WorldRenderContext context) {
    }

    default void worldRenderAfterSetup(WorldRenderContext context) {
    }

    default void worldRenderAfterTranslucent(WorldRenderContext context) {
    }

    default void worldRenderBeforeDebugRender(WorldRenderContext context) {
    }

    default Result worldRenderBlockOutline(WorldRenderContext context, WorldRenderContext.BlockOutlineContext blockOutlineContext) {
        return Result.PASS;
    }

    default void worldRenderStart(WorldRenderContext context) {
    }

}
