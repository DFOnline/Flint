package dev.dfonline.flint.actiondump.shop;

import dev.dfonline.flint.actiondump.data.Icon;

public record ShopType(
        String id,
        int slot,
        String name,
        Icon icon,
        Purchasable[] purchasables
) {
    public record Purchasable(
            Icon item,
            String id,
            int price,
            String currencyType
    ) {

    }
}
