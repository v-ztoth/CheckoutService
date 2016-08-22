package com.itv.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class PricingRule
{
    private final Integer itemCount;

    private final BigDecimal specialPrice;

    private final ItemIdentifier itemIdentifier;

    public PricingRule(Integer itemCount, BigDecimal specialPrice, ItemIdentifier itemIdentifier)
    {
        this.itemCount = itemCount;
        this.specialPrice = specialPrice;
        this.itemIdentifier = itemIdentifier;
    }

    public Integer getItemCount()
    {
        return itemCount;
    }

    public BigDecimal getSpecialPrice()
    {
        return specialPrice;
    }

    public ItemIdentifier getItemIdentifier()
    {
        return itemIdentifier;
    }
}
