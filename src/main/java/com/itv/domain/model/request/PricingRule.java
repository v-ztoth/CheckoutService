package com.itv.domain.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class PricingRule
{
    private final Integer itemNumber;

    private final BigDecimal specialUnitPrice;

    public PricingRule(Integer itemNumber, BigDecimal specialUnitPrice)
    {
        this.itemNumber = itemNumber;
        this.specialUnitPrice = specialUnitPrice;
    }

    public Integer getItemNumber()
    {
        return itemNumber;
    }

    public BigDecimal getSpecialUnitPrice()
    {
        return specialUnitPrice;
    }
}
