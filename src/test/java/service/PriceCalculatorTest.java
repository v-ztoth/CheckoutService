package service;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"unit"})
public class PriceCalculatorTest
{
    private PriceCalculator priceCalculator;
    private Long actualPrice;
    private Long exceptedPrice;

    @BeforeMethod
    public void setup()
    {
        priceCalculator = null;
        actualPrice = null;
        exceptedPrice = null;
    }

    @Test
    public void testCalculate()
    {
        givenAPriceCalculator();
        givenExceptedPrice();
        whenCalculateCalled();
        thenPriceCalculated();
    }

    private void givenAPriceCalculator()
    {
        priceCalculator = new PriceCalculator();
    }

    private void givenExceptedPrice()
    {
        exceptedPrice = 10L;
    }

    private void whenCalculateCalled()
    {
        actualPrice = priceCalculator.calculate();
    }

    private void thenPriceCalculated()
    {
        Assert.assertEquals(actualPrice, exceptedPrice, "Actual price should be" + exceptedPrice);
    }
}
