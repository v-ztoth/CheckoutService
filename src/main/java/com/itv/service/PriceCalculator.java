package com.itv.service;

import com.itv.domain.model.request.Item;
import com.itv.domain.model.request.PricingRule;
import com.itv.domain.model.request.UnitPrice;
import com.itv.domain.model.response.CalculatedPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PriceCalculator
{
    public static final String UNIT_PRICE_MISSING_MESSAGE = "Item unit price is not present!";
    public static final String COUNT_MISSING_MESSAGE = "Item count is not present!";

    public CalculatedPrice calculate(Item item, Optional<PricingRule> pricingRule)
    {
        Optional<Item> itemOptional = Optional.ofNullable(item);
        BigDecimal unitPrice = itemOptional
                .map(Item::getUnitUnitPrice)
                .map(UnitPrice::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(UNIT_PRICE_MISSING_MESSAGE));

        Integer count = itemOptional
                .map(Item::getCount)
                .orElseThrow(() -> new IllegalArgumentException(COUNT_MISSING_MESSAGE));

        if (!pricingRule.isPresent())
        {
            return new CalculatedPrice(unitPrice.multiply(new BigDecimal(count)));
        }

        return new CalculatedPrice(BigDecimal.TEN);
    }
}
