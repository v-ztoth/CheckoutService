package service;

import domain.model.request.Item;
import domain.model.request.ItemIdentifier;
import domain.model.request.PricingRule;
import domain.model.request.UnitPrice;
import domain.model.response.CalculatedPrice;
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
        UnitPrice unitPrice = new UnitPrice(20L);
        ItemIdentifier itemIdentifier = new ItemIdentifier("C");
        Integer count = 2;

        givenAPriceCalculator();
        givenExceptedPrice(unitPrice, count);
        givenAnItem(itemIdentifier, unitPrice, count);
        givenAnEmptyPricingRule();
        whenCalculateCalled();
        thenPriceCalculated();
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
