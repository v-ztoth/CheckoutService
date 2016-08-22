package com.itv.services;

import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import com.itv.domain.model.UnitPrice;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PriceCalculator implements IPriceCalculator
{
    public static final String COUNT_MISSING_MESSAGE = "Item count is not present!";
    public static final String UNIT_PRICE_MISSING_MESSAGE = "Item unit price is not present!";
    public static final String PRICING_RULE_COUNT_MISSING_MESSAGE = "Pricing rule count is not present!";
    public static final String PRICING_RULE_PRICE_MISSING_MESSAGE = "Pricing rule special price is not present!";
    public static final String INVALID_PRICING_RULE_MESSAGE = "Pricing rule cannot be applied. Item count is not equal with the excepted count";

    @Override
    public CalculatedPrice calculate(List<Item> items, Set<PricingRule> pricingRules)
    {
        List<CalculatedPrice> prices = items.stream()
                .filter(Objects::nonNull)
                .map(i -> calculate(i, getPricingRuleByIdentifier(i.getItemIdentifier(), pricingRules)))
                .collect(
                    Collectors.toList()
                );

        BigDecimal calculatedAmount =  prices.stream()
                .map(CalculatedPrice::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CalculatedPrice(calculatedAmount);
    }

    private Optional<PricingRule> getPricingRuleByIdentifier(ItemIdentifier itemIdentifier, Set<PricingRule> pricingRules)
    {
        if (CollectionUtils.isEmpty(pricingRules))
        {
            return Optional.empty();
        }

        return pricingRules.stream().filter(p -> p.getItemIdentifier().equals(itemIdentifier)).findFirst();
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
                .orElseThrow(() -> new IllegalArgumentException(UNIT_PRICE_MISSING_MESSAGE));

        if (isPricingRuleApplicable(pricingRule, item))
        {
            return handlePricingRule(item, pricingRule);
        }

        return new CalculatedPrice(unitPrice.multiply(new BigDecimal(count)));
    }

    private boolean isPricingRuleApplicable(Optional<PricingRule> pricingRule, Item item)
    {
        return pricingRule.isPresent() && item.getCount().equals(pricingRule.get().getItemCount());
    }

    // The discount is applied for 3 "A" item only according to the task.
    // Because the task does not say anything about what to do in case if there are 4 "A" item I did not handle that case.
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
