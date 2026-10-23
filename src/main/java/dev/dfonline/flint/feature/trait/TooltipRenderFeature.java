package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * A feature that can modify item tooltips.
 */
public interface TooltipRenderFeature extends FeatureTrait {

    /**
     * Called when an item tooltip is being rendered.
     *
     * @param item    The item stack being hovered over
     * @param context The tooltip context
     * @param type    The tooltip type
     * @param lore    The list of tooltip text lines that can be modified
     */
    void tooltipRender(ItemStack item, Item.TooltipContext context, TooltipFlag type, List<Component> lore);

}
