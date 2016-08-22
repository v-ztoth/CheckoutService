package com.itv.domain.services;

import com.itv.domain.model.CalculatedPrice;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import com.itv.domain.model.UnitPrice;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Test(groups = {"unit"})
public class PriceCalculatorTest
{
    private CalculatedPrice actualPrice;
    private CalculatedPrice exceptedPrice;
    private List<Item> items;
    private Set<PricingRule> pricingRules;
    private PriceCalculator priceCalculator;

    @BeforeMethod
    public void setup()
    {
        priceCalculator = null;
        actualPrice = null;
        exceptedPrice = null;
        items = null;
        pricingRules = null;
    }

    @Test()
    public void testCalculateWithoutPricingRule()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();

        Item itemA = new Item(new ItemIdentifier("A"), 2, new UnitPrice(new BigDecimal(50)));
        Item itemB = new Item(new ItemIdentifier("B"), 1, new UnitPrice(new BigDecimal(30)));
        Item itemC = new Item(new ItemIdentifier("C"), 1, new UnitPrice(new BigDecimal(20)));
        Item itemD = new Item(new ItemIdentifier("D"), 1, new UnitPrice(new BigDecimal(15)));

        itemList.add(itemA);
        itemList.add(itemB);
        itemList.add(itemC);
        itemList.add(itemD);

        givenAnItemList(itemList);

        givenExceptedPrice(itemList);

        whenCalculateCalled();

        thenPriceCalculated();
    }

    @Test
    public void testCalculateWithNullItem()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();
        Item itemA = null;
        itemList.add(itemA);

        givenAnItemList(itemList);

        givenExceptedPrice(BigDecimal.ZERO);

        whenCalculateCalled();

        thenPriceCalculated();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateWithNullUnitPrice()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();
        Item itemA = new Item(new ItemIdentifier("A"), 1, null);
        itemList.add(itemA);

        givenAnItemList(itemList);

        whenCalculateCalled();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateWithItemWithNoCount()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();
        Item itemA = new Item(new ItemIdentifier("A"), null, new UnitPrice(new BigDecimal(50)));
        itemList.add(itemA);

        givenAnItemList(itemList);

        whenCalculateCalled();
    }

    @Test
    public void testCalculateWithApplicablePricingRule()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();

        Item itemA = new Item(new ItemIdentifier("A"), 3, new UnitPrice(new BigDecimal(50)));
        Item itemB = new Item(new ItemIdentifier("B"), 2, new UnitPrice(new BigDecimal(30)));

        itemList.add(itemA);
        itemList.add(itemB);

        givenAnItemList(itemList);


        Set<PricingRule> pricingRules = new HashSet<>();

        PricingRule pricingRuleA = new PricingRule(3, new BigDecimal(130), new ItemIdentifier("A"));
        PricingRule pricingRuleB = new PricingRule(2, new BigDecimal(45), new ItemIdentifier("B"));

        pricingRules.add(pricingRuleA);
        pricingRules.add(pricingRuleB);

        givenAPricingRule(pricingRules);

        givenExceptedPrice(new BigDecimal(175));

        whenCalculateCalled();

        thenPriceCalculated();
    }

    @Test
    public void testCalculateWithNotApplicablePricingRule()
    {
        givenPriceCalculator();

        List<Item> itemList = new ArrayList<>();

        Item itemA = new Item(new ItemIdentifier("A"), 2, new UnitPrice(new BigDecimal(50)));
        Item itemB = new Item(new ItemIdentifier("B"), 1, new UnitPrice(new BigDecimal(30)));

        itemList.add(itemA);
        itemList.add(itemB);

        givenAnItemList(itemList);


        Set<PricingRule> pricingRules = new HashSet<>();

        PricingRule pricingRuleA = new PricingRule(3, new BigDecimal(130), new ItemIdentifier("A"));
        PricingRule pricingRuleB = new PricingRule(2, new BigDecimal(45), new ItemIdentifier("B"));

        pricingRules.add(pricingRuleA);
        pricingRules.add(pricingRuleB);

        givenAPricingRule(pricingRules);

        givenExceptedPrice(new BigDecimal(130));

        whenCalculateCalled();

        thenPriceCalculated();
    }

    private void givenPriceCalculator()
    {
        priceCalculator = new PriceCalculator();
    }

    private void givenExceptedPrice(List<Item> itemList)
    {
        List<BigDecimal> prices = itemList.stream()
                .map(item -> item.getUnitPrice().getPrice().multiply(new BigDecimal(item.getCount())))
                .collect(
                    Collectors.toList()
                );

        BigDecimal calculatedAmount =  prices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        exceptedPrice = new CalculatedPrice(calculatedAmount);
    }

    private void givenExceptedPrice(BigDecimal price)
    {
        exceptedPrice = new CalculatedPrice(price);
    }

    private void givenAnItemList(List<Item> itemList)
    {
        items = itemList;
    }

    private void givenAPricingRule(Set<PricingRule> pricingRuleSet)
    {
        pricingRules = pricingRuleSet;
    }

    private void whenCalculateCalled()
    {
        actualPrice = priceCalculator.calculate(items, pricingRules);
    }

    private void thenPriceCalculated()
    {
        Assert.assertNotNull(actualPrice, "Calculated price should not be null!");
        Assert.assertEquals(actualPrice, exceptedPrice, "Actual price should be " + exceptedPrice);
    }
}
