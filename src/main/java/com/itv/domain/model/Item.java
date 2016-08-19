package com.itv.domain.model;

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
