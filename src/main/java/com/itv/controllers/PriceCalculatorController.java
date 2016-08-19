package com.itv.controllers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.mappers.ItemMapper;
import com.itv.domain.mappers.PricingRuleMapper;
import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.PricingRule;
import com.itv.services.PriceCalculator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/calculatePrice")
public class PriceCalculatorController
{
    private static final Logger LOGGER = Logger.getLogger(PriceCalculatorController.class);

    private PriceCalculator priceCalculator;

    private ItemMapper itemMapper;

    private PricingRuleMapper pricingRuleMapper;

    @Autowired
    public PriceCalculatorController(PriceCalculator priceCalculator, ItemMapper itemMapper,
            PricingRuleMapper pricingRuleMapper)
    {
        this.priceCalculator = priceCalculator;
        this.itemMapper = itemMapper;
        this.pricingRuleMapper = pricingRuleMapper;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculatedPrice> calculatePrice(@Valid PricingRequest pricingRequest)
    {
        Item item = itemMapper.map(pricingRequest);
        Optional<PricingRule> pricingRule = pricingRuleMapper.map(pricingRequest);

        CalculatedPrice calculatedPrice = priceCalculator.calculate(item, pricingRule);

        return new ResponseEntity<>(calculatedPrice, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(Exception exception)
    {
        LOGGER.error(exception);

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
