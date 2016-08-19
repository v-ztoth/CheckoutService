package com.itv.domain.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class PricingRule
{
    private final Integer itemNumber;

    private final Long specialUnitPrice;

    public PricingRule(Integer itemNumber, Long specialUnitPrice)
    {
        this.itemNumber = itemNumber;
        this.specialUnitPrice = specialUnitPrice;
    }

    public Integer getItemNumber()
    {
        return itemNumber;
    }

    public Long getSpecialUnitPrice()
    {
        return specialUnitPrice;
    }
}
