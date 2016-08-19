package com.itv.controllers;

import com.itv.controllers.model.PricingRequest;
import com.itv.services.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PriceCalculatorController
{
    private PriceCalculator priceCalculator;

    @Autowired
    public PriceCalculatorController(PriceCalculator priceCalculator)
    {
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> calculatePrice(@Valid PricingRequest pricingRequest)
    {
        return null;
    }
}
