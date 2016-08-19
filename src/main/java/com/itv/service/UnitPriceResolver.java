package com.itv.service;

import com.itv.domain.model.UnitPrice;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UnitPriceResolver
{
    // The unit price should come from a database but for the sake of simplicity I used a simple mapping here
    public UnitPrice getUnitPrice(String itemIdentifier)
    {
        switch (itemIdentifier)
        {
            case "A":
                return new UnitPrice(new BigDecimal(50));
            case "B":
                return new UnitPrice(new BigDecimal(30));
            case "C":
                return new UnitPrice(new BigDecimal(20));
            case "D":
                return new UnitPrice(new BigDecimal(15));
            default:
                throw new IllegalArgumentException("Identifier " + itemIdentifier + " not supported!");
        }
    }
}
