package com.itv.api;

import com.itv.api.model.PricingRequest;
import com.itv.domain.mappers.IItemMapper;
import com.itv.domain.mappers.IPricingRuleMapper;
import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.PricingRule;
import com.itv.services.IPriceCalculator;
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
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/calculatePrice")
public class PriceCalculatorController
{
    private static final Logger LOGGER = Logger.getLogger(PriceCalculatorController.class);

    private IPriceCalculator priceCalculator;

    private IItemMapper itemMapper;

    private IPricingRuleMapper pricingRuleMapper;

    @Autowired
    public PriceCalculatorController(IPriceCalculator priceCalculator, IItemMapper itemMapper,
            IPricingRuleMapper pricingRuleMapper)
    {
        this.priceCalculator = priceCalculator;
        this.itemMapper = itemMapper;
        this.pricingRuleMapper = pricingRuleMapper;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculatedPrice> calculatePrice(@Valid PricingRequest pricingRequest)
    {
        List<Item> items = itemMapper.map(pricingRequest);
        Set<PricingRule> pricingRules = pricingRuleMapper.map(pricingRequest);

        CalculatedPrice calculatedPrice = priceCalculator.calculate(items, pricingRules);

        return new ResponseEntity<>(calculatedPrice, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(Exception exception)
    {
        LOGGER.error(exception);

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
