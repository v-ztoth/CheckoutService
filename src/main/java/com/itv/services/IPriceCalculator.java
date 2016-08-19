package com.itv.services;

import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.PricingRule;

import java.util.Optional;

public interface IPriceCalculator
{
    CalculatedPrice calculate(Item item, Optional<PricingRule> pricingRule);
}
