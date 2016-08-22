package com.itv.domain.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PricingRuleMapper implements IPricingRuleMapper
{
    @Override
    public Set<PricingRule> map(PricingRequest pricingRequest)
    {
        Assert.notNull(pricingRequest, "PricingRequest should not be null!");

        Optional<Set<com.itv.api.model.PricingRule>> apiPricingRulesOptional = Optional
                .ofNullable(pricingRequest.getPricingRules());

        if (!apiPricingRulesOptional.isPresent())
        {
            return Collections.emptySet();
        }

        Set<com.itv.api.model.PricingRule> apiPricingRules = apiPricingRulesOptional.get();

        return apiPricingRules.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    private PricingRule map(com.itv.api.model.PricingRule apiPricingRule)
    {
        return new PricingRule(apiPricingRule.getItemCount(), apiPricingRule.getSpecialPrice(),
                new ItemIdentifier(apiPricingRule.getItemIdentifier()));
    }
}
