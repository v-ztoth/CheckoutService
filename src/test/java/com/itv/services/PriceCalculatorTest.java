package com.itv.services;

import org.testng.annotations.Test;

@Test(groups = {"unit"})
public class PriceCalculatorTest
{
//    private static final ItemIdentifier ITEM_IDENTIFIER = new ItemIdentifier("C");
//    private static final Integer COUNT = 2;
//
//    private CalculatedPrice actualPrice;
//    private CalculatedPrice exceptedPrice;
//    private Item item;
//    private Optional<PricingRule> pricingRule;
//
//    @InjectMocks
//    private PriceCalculator priceCalculator;
//
//    @BeforeMethod
//    public void setup()
//    {
//        priceCalculator = null;
//        actualPrice = null;
//        exceptedPrice = null;
//        item = null;
//        pricingRule = Optional.empty();
//
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @DataProvider(name = "identifierProvider")
//    public static Object[][] identifiers() {
//        return new Object[][] {
//                {new ItemIdentifier("A"), new UnitPrice(new BigDecimal(50))},
//                {new ItemIdentifier("B"), new UnitPrice(new BigDecimal(30))},
//                {new ItemIdentifier("C"), new UnitPrice(new BigDecimal(20))},
//                {new ItemIdentifier("D"), new UnitPrice(new BigDecimal(15))}
//        };
//    }
//
//    @Test(dataProvider = "identifierProvider")
//    public void testCalculateWithoutPricingRule(ItemIdentifier identifier, UnitPrice unitPrice)
//    {
//        givenExceptedPrice(unitPrice, COUNT);
//        givenAnItem(identifier, COUNT);
//        givenAnEmptyPricingRule();
//        givenAUnitPrice();
//
//        whenCalculateCalled();
//
//        thenPriceCalculated();
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testCalculateWithNullItem()
//    {
//        givenAnEmptyPricingRule();
//
//        whenCalculateCalled();
//
//        thenPriceCalculated();
//    }
//
//    @Test
//    public void testCalculateWithItemWithNoIdentifier()
//    {
//        givenAnItem(null, COUNT);
//        givenAnEmptyPricingRule();
//
//        try
//        {
//            whenCalculateCalled();
//            Assert.fail();
//        }
//        catch (IllegalArgumentException ex)
//        {
//            Assert.assertEquals(ex.getMessage(), PriceCalculator.IDENTIFIER_MISSING_MESSAGE);
//        }
//    }
//
//    @Test
//    public void testCalculateWithItemWithNoCount()
//    {
//        givenAnItem(ITEM_IDENTIFIER, null);
//        givenAnEmptyPricingRule();
//
//        try
//        {
//            whenCalculateCalled();
//            Assert.fail();
//        }
//        catch (IllegalArgumentException ex)
//        {
//            Assert.assertEquals(ex.getMessage(), PriceCalculator.COUNT_MISSING_MESSAGE);
//        }
//    }
//
//    @Test
//    public void testCalculateWithPricingRule()
//    {
//        String identifier = "A";
//        BigDecimal specialPrice = givenASpecialPrice(identifier);
//        int itemCount = 3;
//
//        givenAnItem(new ItemIdentifier(identifier), itemCount, new UnitPrice(new BigDecimal(50)));
//        givenAPricingRule(itemCount, specialPrice);
//        givenExceptedPrice(specialPrice);
//        givenAUnitPrice();
//
//        whenCalculateCalled();
//
//        thenPriceCalculated();
//    }
//
//    private void givenExceptedPrice(UnitPrice unitPrice, Integer count)
//    {
//        BigDecimal price = unitPrice.getPrice().multiply(new BigDecimal(count));
//        exceptedPrice = new CalculatedPrice(price);
//    }
//
//    private void givenExceptedPrice(BigDecimal specialPrice)
//    {
//        exceptedPrice = new CalculatedPrice(specialPrice);
//    }
//
//    private void givenAnItem(ItemIdentifier itemIdentifier, Integer count, UnitPrice unitPrice)
//    {
//        item = new Item(itemIdentifier, count, unitPrice);
//    }
//
//    private void givenAnEmptyPricingRule()
//    {
//        pricingRule = Optional.empty();
//    }
//
//    private void givenAPricingRule(Integer itemCount, BigDecimal specialPrice)
//    {
//        pricingRule = Optional.of(new PricingRule(itemCount, specialPrice));
//    }
//
//    private void givenAUnitPrice()
//    {
//        when(unitPriceResolver.getUnitPrice("A"))
//                .thenReturn(new UnitPrice(new BigDecimal(50)));
//
//        when(unitPriceResolver.getUnitPrice("B"))
//                .thenReturn(new UnitPrice(new BigDecimal(30)));
//
//        when(unitPriceResolver.getUnitPrice("C"))
//                .thenReturn(new UnitPrice(new BigDecimal(20)));
//
//        when(unitPriceResolver.getUnitPrice("D"))
//                .thenReturn(new UnitPrice(new BigDecimal(15)));
//    }
//
//    private BigDecimal givenASpecialPrice(String identifier)
//    {
//        switch (identifier)
//        {
//            case "A":
//                return new BigDecimal(130);
//            case "B":
//                return new BigDecimal(45);
//            default:
//                throw new IllegalArgumentException("Identifier " + identifier + " not supported!");
//        }
//    }
//
//    private void whenCalculateCalled()
//    {
//        actualPrice = priceCalculator.calculate(item, pricingRule);
//    }
//
//    private void thenPriceCalculated()
//    {
//        Assert.assertNotNull(actualPrice, "Calculated price should not be null!");
//        Assert.assertEquals(actualPrice, exceptedPrice, "Actual price should be " + exceptedPrice);
//    }
}
