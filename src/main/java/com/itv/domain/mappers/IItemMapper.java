package com.itv.domain.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.model.Item;

public interface IItemMapper
{
    Item map(PricingRequest pricingRequest);
}
