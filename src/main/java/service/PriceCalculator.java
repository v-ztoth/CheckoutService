package service;

import domain.model.request.Item;
import domain.model.request.PricingRule;
import domain.model.request.UnitPrice;
import domain.model.response.CalculatedPrice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceCalculator
{
    public CalculatedPrice calculate(Item item, Optional<PricingRule> pricingRule)
    {
        Optional<Item> itemOptional = Optional.ofNullable(item);
        Long unitPrice = itemOptional.map(Item::getUnitUnitPrice).map(UnitPrice::getPrice).orElseThrow(() -> new IllegalArgumentException("Item unit price is not present!"));
        Integer count = itemOptional.map(Item::getCount).orElseThrow(() -> new IllegalArgumentException("Item count is not present!"));

        if (!pricingRule.isPresent())
        {
            return new CalculatedPrice(unitPrice * count);
        }

        return new CalculatedPrice(10L);
    }
}
