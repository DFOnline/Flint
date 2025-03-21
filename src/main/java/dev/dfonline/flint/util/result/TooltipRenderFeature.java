package dev.dfonline.flint.feature.trait.results;

import dev.dfonline.flint.feature.trait.FeatureTrait;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public interface TooltipRenderFeature extends FeatureTrait {
    void tooltipRender(ItemStack item, Item.TooltipContext context, TooltipType type, List<Text> lore);
}
