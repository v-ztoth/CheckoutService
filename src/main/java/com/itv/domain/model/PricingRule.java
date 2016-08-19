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

    public PricingRule(Integer itemCount, BigDecimal specialPrice)
    {
        this.itemCount = itemCount;
        this.specialPrice = specialPrice;
    }

    public Integer getItemCount()
    {
        return itemCount;
    }

    public BigDecimal getSpecialPrice()
    {
        return specialPrice;
    }
}
