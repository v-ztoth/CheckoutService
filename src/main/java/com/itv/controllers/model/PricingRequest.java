package com.itv.controllers.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class PricingRequest
{
    @NotNull
    @Size(min = 1, max = 1, message = "itemIdentifier should be one character")
    private String itemIdentifier;

    @NotNull
    @Min(value = 1, message = "itemCount cannot be less than 1")
    private Integer itemCount;

    @Min(value = 1, message = "discountedItemCount cannot be less than 1")
    private Integer discountedItemCount;

    private BigDecimal discountedPrice;

    public String getItemIdentifier()
    {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier)
    {
        this.itemIdentifier = itemIdentifier;
    }

    public Integer getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(Integer itemCount)
    {
        this.itemCount = itemCount;
    }
    public Integer getDiscountedItemCount()
    {
        return discountedItemCount;
    }

    public void setDiscountedItemCount(Integer discountedItemCount)
    {
        this.discountedItemCount = discountedItemCount;
    }

    public BigDecimal getDiscountedPrice()
    {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice)
    {
        this.discountedPrice = discountedPrice;
    }
}
