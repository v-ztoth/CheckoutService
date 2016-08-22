package com.itv.api.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@ToString
@EqualsAndHashCode
public class PricingRequest
{
    @NotNull
    @Valid
    private List<Item> items;

    @Valid
    private Set<PricingRule> pricingRules;

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }

    public Set<PricingRule> getPricingRules()
    {
        return pricingRules;
    }

    public void setPricingRules(Set<PricingRule> pricingRules)
    {
        this.pricingRules = pricingRules;
    }
}
