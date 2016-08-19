package com.itv.services;

import com.itv.domain.model.UnitPrice;

public interface IUnitPriceResolver
{
    UnitPrice getUnitPrice(String itemIdentifier);
}
