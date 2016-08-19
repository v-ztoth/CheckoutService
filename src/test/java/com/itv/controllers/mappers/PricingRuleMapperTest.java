package com.itv.controllers.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.mappers.PricingRuleMapper;
import com.itv.domain.model.PricingRule;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Test(groups = {"unit"})
public class PricingRuleMapperTest
{
    private PricingRuleMapper pricingRuleMapper;
    private PricingRequest pricingRequest;
    private PricingRule actualPricingRule;
    private PricingRule exceptedPricingRule;

    @BeforeMethod
    public void setup()
    {
        pricingRuleMapper = null;
        pricingRequest = null;
        actualPricingRule = null;
        exceptedPricingRule = null;
    }

    @Test
    public void mapTest()
    {
        givenAPricingRuleMapper();
        givenAPricingRequest();
        givenExceptedPricingRule();

        whenMapCalled();

        thenItemShouldBeMapped();
    }

    private void givenAPricingRuleMapper()
    {
        pricingRuleMapper = new PricingRuleMapper();
    }

    private void givenAPricingRequest()
    {
        pricingRequest = new PricingRequest();
        pricingRequest.setItemCount(3);
        pricingRequest.setItemIdentifier("A");
        pricingRequest.setDiscountedPrice(new BigDecimal(130));
        pricingRequest.setDiscountedItemCount(3);
    }

    private void givenExceptedPricingRule()
    {
        exceptedPricingRule = new PricingRule(3, new BigDecimal(130));
    }

    private void whenMapCalled()
    {
        actualPricingRule = pricingRuleMapper.map(pricingRequest);
    }

    private void thenItemShouldBeMapped()
    {
        Assert.assertEquals(actualPricingRule, exceptedPricingRule);
    }

}
