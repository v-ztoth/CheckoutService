package com.itv.domain.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.model.PricingRule;

import java.util.Optional;

public interface IPricingRuleMapper
{
    Optional<PricingRule> map(PricingRequest pricingRequest);
}
