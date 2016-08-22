package com.itv.api.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class Item
{
    @NotNull
    @Size(min = 1, max = 1, message = "itemIdentifier should be one character")
    private final String identifier;

    @NotNull
    private final BigDecimal unitPrice;

    public Item(String identifier, BigDecimal unitPrice)
    {
        this.identifier = identifier;
        this.unitPrice = unitPrice;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }
}
