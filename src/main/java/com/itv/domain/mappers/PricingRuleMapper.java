package com.itv.domain.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.model.PricingRule;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PricingRuleMapper implements IPricingRuleMapper
{
    @Override
    public Optional<PricingRule> map(PricingRequest pricingRequest)
    {
        Assert.notNull(pricingRequest, "PricingRequest should not be null!");

        Integer discountedItemCount = pricingRequest.getDiscountedItemCount();

        BigDecimal discountedPrice = pricingRequest.getDiscountedPrice();

        if (discountedItemCount == null || discountedPrice == null)
        {
            return Optional.empty();
        }

        return Optional.of(new PricingRule(discountedItemCount, discountedPrice));
    }
}
