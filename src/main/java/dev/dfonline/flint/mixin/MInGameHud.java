package dev.dfonline.flint.mixin;

import dev.dfonline.flint.Flint;
import dev.dfonline.flint.feature.trait.FeatureTraitType;
import dev.dfonline.flint.feature.trait.RenderedFeature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MInGameHud {

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Flint.FEATURE_MANAGER.getByTrait(FeatureTraitType.RENDERED).forEach(feature -> {
            ((RenderedFeature) feature).render(context, tickCounter);
        });
    }

}
