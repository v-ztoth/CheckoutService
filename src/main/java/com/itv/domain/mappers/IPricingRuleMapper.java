package com.itv.domain.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.model.PricingRule;

import java.util.Set;

public interface IPricingRuleMapper
{
    Set<PricingRule> map(PricingRequest pricingRequest);
}
