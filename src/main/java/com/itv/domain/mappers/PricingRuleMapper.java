package com.itv.domain.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.model.PricingRule;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Component
public class PricingRuleMapper
{
    public PricingRule map(PricingRequest pricingRequest)
    {
        Assert.notNull(pricingRequest, "PricingRequest should not be null!");

        Integer discountedItemCount = pricingRequest.getDiscountedItemCount();
        Assert.notNull(discountedItemCount, "discountedItemCount should not be null!");

        BigDecimal discountedPrice = pricingRequest.getDiscountedPrice();
        Assert.notNull(discountedPrice, "discountedPrice should not be null!");

        return new PricingRule(discountedItemCount, discountedPrice);
    }
}
