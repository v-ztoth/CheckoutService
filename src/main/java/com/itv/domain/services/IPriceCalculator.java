package com.itv.domain.services;

import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.PricingRule;

import java.util.List;
import java.util.Set;

public interface IPriceCalculator
{
    CalculatedPrice calculate(List<Item> item, Set<PricingRule> pricingRules);
}
