package com.itv.api.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class PricingRule
{
    @NotNull
    @Size(min = 1, max = 1, message = "itemIdentifier should be one character")
    private String itemIdentifier;

    @NotNull
    @Min(value = 1, message = "discountedItemCount cannot be less than 1")
    private Integer itemCount;

    @NotNull
    private BigDecimal specialPrice;

    public PricingRule()
    {

    }

    public PricingRule(String itemIdentifier, Integer itemCount, BigDecimal specialPrice)
    {
        this.itemIdentifier = itemIdentifier;
        this.itemCount = itemCount;
        this.specialPrice = specialPrice;
    }

    public String getItemIdentifier()
    {
        return itemIdentifier;
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
