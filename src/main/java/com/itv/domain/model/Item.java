package com.itv.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Item
{
    private final ItemIdentifier itemIdentifier;

    private final Integer count;

    private final UnitPrice unitPrice;

    public Item(ItemIdentifier itemIdentifier, Integer count, UnitPrice unitPrice)
    {
        this.itemIdentifier = itemIdentifier;
        this.count = count;
        this.unitPrice = unitPrice;
    }

    public ItemIdentifier getItemIdentifier()
    {
        return itemIdentifier;
    }

    public Integer getCount()
    {
        return count;
    }

    public UnitPrice getUnitPrice()
    {
        return unitPrice;
    }
}
