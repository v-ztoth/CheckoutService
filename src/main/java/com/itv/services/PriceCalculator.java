package com.itv.services;

import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.PricingRule;
import com.itv.domain.model.UnitPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PriceCalculator implements IPriceCalculator
{
    public static final String IDENTIFIER_MISSING_MESSAGE = "Item identifier is not present!";
    public static final String COUNT_MISSING_MESSAGE = "Item count is not present!";
    public static final String PRICING_RULE_COUNT_MISSING_MESSAGE = "Pricing rule count is not present!";
    public static final String PRICING_RULE_PRICE_MISSING_MESSAGE = "Pricing rule special price is not present!";
    public static final String INVALID_PRICING_RULE_MESSAGE = "Pricing rule cannot be applied. Item count is not equal with the excepted count";

    @Override
    public CalculatedPrice calculate(List<Item> items, Set<PricingRule> pricingRules)
    {
        return null;
    }

    private CalculatedPrice calculate(Item item, Optional<PricingRule> pricingRule)
    {
        Optional<Item> itemOptional = Optional.ofNullable(item);

        Integer count = itemOptional
                .map(Item::getCount)
                .orElseThrow(() -> new IllegalArgumentException(COUNT_MISSING_MESSAGE));

        BigDecimal unitPrice = itemOptional
                .map(Item::getUnitPrice)
                .map(UnitPrice::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(COUNT_MISSING_MESSAGE));

        if (!pricingRule.isPresent())
        {
            return new CalculatedPrice(unitPrice.multiply(new BigDecimal(count)));
        }

        return handlePricingRule(item, pricingRule);
    }

    // The discount is applied for 3 "A" item only according to the task.
    // Because the task does not say what to do in case if there are 4 "A" item I did not handle that case.
    // This case should be clarified with the product owner.
    private CalculatedPrice handlePricingRule(Item item, Optional<PricingRule> pricingRule)
    {
        Integer countInPricingRule = pricingRule
                .map(PricingRule::getItemCount)
                .orElseThrow(() -> new IllegalArgumentException(PRICING_RULE_COUNT_MISSING_MESSAGE));

        BigDecimal specialPrice = pricingRule
                .map(PricingRule::getSpecialPrice)
                .orElseThrow(() -> new IllegalArgumentException(PRICING_RULE_PRICE_MISSING_MESSAGE));

        if (!validateCounts(item, countInPricingRule))
        {
            throw new IllegalArgumentException(INVALID_PRICING_RULE_MESSAGE);
        }

        return new CalculatedPrice(specialPrice);
    }

    private boolean validateCounts(Item item, Integer countInPricingRule)
    {
        return item.getCount().equals(countInPricingRule);
    }
}
