package com.itv.domain.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ItemMapper
{
    public Item map(PricingRequest pricingRequest)
    {
        Assert.notNull(pricingRequest, "PricingRequest should not be null!");

        String itemIdentifier = pricingRequest.getItemIdentifier();
        Assert.notNull(itemIdentifier, "ItemIdentifier should not be null!");

        Integer itemCount = pricingRequest.getItemCount();
        Assert.notNull(itemCount, "ItemIdentifier should not be null!");

        return new Item(new ItemIdentifier(itemIdentifier), itemCount);
    }
}
