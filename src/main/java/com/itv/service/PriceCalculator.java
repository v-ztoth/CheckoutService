package com.itv.service;

import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import com.itv.domain.model.CalculatedPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PriceCalculator
{
    public static final String IDENTIFIER_MISSING_MESSAGE = "Item identifier is not present!";
    public static final String COUNT_MISSING_MESSAGE = "Item count is not present!";
    public static final String PRICING_RULE_COUNT_MISSING_MESSAGE = "Pricing rule count is not present!";
    public static final String PRICING_RULE_PRICE_MISSING_MESSAGE = "Pricing rule special price is not present!";
    public static final String INVALID_PRICING_RULE_MESSAGE = "Pricing rule cannot be applied. Item count is not equal with the excepted count";

    private UnitPriceResolver unitPriceResolver;

    @Autowired
    public PriceCalculator(UnitPriceResolver unitPriceResolver)
    {
        this.unitPriceResolver = unitPriceResolver;
    }

    public CalculatedPrice calculate(Item item, Optional<PricingRule> pricingRule)
    {
        Optional<Item> itemOptional = Optional.ofNullable(item);

        String identifier = itemOptional.map(Item::getItemIdentifier)
                .map(ItemIdentifier::getIdentifier)
                .orElseThrow(() -> new IllegalArgumentException(IDENTIFIER_MISSING_MESSAGE));

        Integer count = itemOptional
                .map(Item::getCount)
                .orElseThrow(() -> new IllegalArgumentException(COUNT_MISSING_MESSAGE));

        BigDecimal unitPrice = unitPriceResolver.getUnitPrice(identifier).getPrice();

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
            throw new IllegalArgumentException("INVALID_PRICING_RULE_MESSAGE");
        }

        return new CalculatedPrice(specialPrice);
    }

    private boolean validateCounts(Item item, Integer countInPricingRule)
    {
        return item.getCount().equals(countInPricingRule);
    }
}
