package dev.dfonline.flint.feature.trait;

import dev.dfonline.flint.feature.core.FeatureTrait;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

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
    void tooltipRender(ItemStack item, Item.TooltipContext context, TooltipType type, List<Text> lore);

}
