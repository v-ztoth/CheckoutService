package com.itv.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Item
{
    private final ItemIdentifier itemIdentifier;

    private final Integer count;

    public Item(ItemIdentifier itemIdentifier, Integer count)
    {
        this.itemIdentifier = itemIdentifier;
        this.count = count;
    }

    public ItemIdentifier getItemIdentifier()
    {
        return itemIdentifier;
    }

    public Integer getCount()
    {
        return count;
    }
}
