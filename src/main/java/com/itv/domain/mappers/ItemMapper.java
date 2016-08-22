package com.itv.domain.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.UnitPrice;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ItemMapper implements IItemMapper
{
    @Override
    public List<Item> map(PricingRequest pricingRequest)
    {
        Assert.notNull(pricingRequest, "PricingRequest should not be null!");

        List<com.itv.api.model.Item> apiItems = pricingRequest.getItems();
        Assert.notNull(apiItems, "Items should not be null!");
        Assert.notEmpty(apiItems, "Items should not be empty!");

        Map<com.itv.api.model.Item, Long> groupedItems = apiItems.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );

        return map(groupedItems);
    }

    private List<Item> map(Map<com.itv.api.model.Item, Long> groupedItems)
    {
        List<Item> countedItems = new ArrayList<>();

        for (Map.Entry<com.itv.api.model.Item, Long> entry : groupedItems.entrySet())
        {
            com.itv.api.model.Item apiItem = entry.getKey();
            Long count = entry.getValue();

            Item domainItem = new Item(new ItemIdentifier(apiItem.getIdentifier()), count.intValue(),
                    new UnitPrice(apiItem.getUnitPrice()));

            countedItems.add(domainItem);
        }

        return countedItems;
    }
}
