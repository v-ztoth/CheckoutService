package com.itv.domain.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.model.Item;

import java.util.List;

public interface IItemMapper
{
    List<Item> map(PricingRequest pricingRequest);
}
