package com.itv.service;

import com.itv.domain.model.request.Item;
import com.itv.domain.model.request.ItemIdentifier;
import com.itv.domain.model.request.PricingRule;
import com.itv.domain.model.request.UnitPrice;
import com.itv.domain.model.response.CalculatedPrice;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

@Test(groups = {"unit"})
public class UnitPriceCalculatorTest
{
    private PriceCalculator priceCalculator;
    private CalculatedPrice actualPrice;
    private CalculatedPrice exceptedPrice;
    private Item item;
    private Optional<PricingRule> pricingRule;
    private static final UnitPrice UNIT_PRICE = new UnitPrice(20L);
    private static final ItemIdentifier ITEM_IDENTIFIER = new ItemIdentifier("C");
    private static final Integer COUNT = 2;

    @BeforeMethod
    public void setup()
    {
        priceCalculator = null;
        actualPrice = null;
        exceptedPrice = null;
        item = null;
        pricingRule = null;
    }

    @Test
    public void testCalculate()
    {
        givenAPriceCalculator();
        givenExceptedPrice(UNIT_PRICE, COUNT);
        givenAnItem(ITEM_IDENTIFIER, UNIT_PRICE, COUNT);
        givenAnEmptyPricingRule();
        whenCalculateCalled();
        thenPriceCalculated();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateWithNullItem()
    {
        givenAPriceCalculator();
        givenAnEmptyPricingRule();
        whenCalculateCalled();
        thenPriceCalculated();
    }

    @Test
    public void testCalculateWithItemWithNoUnitPrice()
    {
        givenAPriceCalculator();
        givenAnItem(ITEM_IDENTIFIER, null, COUNT);
        givenAnEmptyPricingRule();

        try
        {
            whenCalculateCalled();
            Assert.fail();
        }
        catch (IllegalArgumentException ex)
        {
            Assert.assertEquals(ex.getMessage(), PriceCalculator.UNIT_PRICE_MISSING_MESSAGE);
        }
    }

    @Test
    public void testCalculateWithItemWithNoCount()
    {
        givenAPriceCalculator();
        givenAnItem(ITEM_IDENTIFIER, UNIT_PRICE, null);
        givenAnEmptyPricingRule();

        try
        {
            whenCalculateCalled();
            Assert.fail();
        }
        catch (IllegalArgumentException ex)
        {
            Assert.assertEquals(ex.getMessage(), PriceCalculator.COUNT_MISSING_MESSAGE);
        }
    }

    private void givenAPriceCalculator()
    {
        priceCalculator = new PriceCalculator();
    }

    private void givenExceptedPrice(UnitPrice unitPrice, Integer count)
    {
        Long price = unitPrice.getPrice() * count;
        exceptedPrice = new CalculatedPrice(price);
    }

    private void givenAnItem(ItemIdentifier itemIdentifier, UnitPrice unitPrice, Integer count)
    {
        item = new Item(itemIdentifier, unitPrice, count);
    }

    private void givenAnEmptyPricingRule()
    {
        pricingRule = Optional.empty();
    }

    private void whenCalculateCalled()
    {
        actualPrice = priceCalculator.calculate(item, pricingRule);
    }

    private void thenPriceCalculated()
    {
        Assert.assertNotNull(actualPrice, "Calculated price should not be null!");
        Assert.assertEquals(actualPrice, exceptedPrice, "Actual price should be " + exceptedPrice);
    }
}
