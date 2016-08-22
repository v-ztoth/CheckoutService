package com.itv.api.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.mappers.PricingRuleMapper;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.PricingRule;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Test(groups = {"unit"})
public class PricingRuleMapperTest
{
    private PricingRuleMapper pricingRuleMapper;
    private PricingRequest pricingRequest;
    private Set<PricingRule> actualPricingRules;
    private Set<PricingRule> exceptedPricingRules;

    @BeforeMethod
    public void setup()
    {
        pricingRuleMapper = null;
        pricingRequest = null;
        actualPricingRules = null;
        exceptedPricingRules = null;
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

    @Test
    public void mapTestWithEmptyPricingRuleSet()
    {
        givenAPricingRuleMapper();
        givenAPricingRequestWithEmptyPricingRuleSet();
        givenExceptedEmptyPricingRule();

        whenMapCalled();

        thenItemShouldBeMapped();
    }

    @Test
    public void mapTestWithNoPricingRuleSet()
    {
        givenAPricingRuleMapper();
        givenAPricingRequestWithNoPricingRuleSet();
        givenExceptedEmptyPricingRule();

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

        Set<com.itv.api.model.PricingRule> pricingRules = new HashSet<>();
        com.itv.api.model.PricingRule pricingRuleA = new com.itv.api.model.PricingRule("A", 3, new BigDecimal(130));
        com.itv.api.model.PricingRule pricingRuleB = new com.itv.api.model.PricingRule("B", 2, new BigDecimal(45));

        pricingRules.add(pricingRuleA);
        pricingRules.add(pricingRuleB);

        pricingRequest.setPricingRules(pricingRules);
    }

    private void givenAPricingRequestWithEmptyPricingRuleSet()
    {
        pricingRequest = new PricingRequest();
        pricingRequest.setPricingRules(Collections.emptySet());
    }

    private void givenAPricingRequestWithNoPricingRuleSet()
    {
        pricingRequest = new PricingRequest();
    }

    private void givenExceptedPricingRule()
    {
        Set<PricingRule> pricingRules = new HashSet<>();

        PricingRule pricingRuleA = new PricingRule(3, new BigDecimal(130), new ItemIdentifier("A"));
        PricingRule pricingRuleB = new PricingRule(2, new BigDecimal(45), new ItemIdentifier("B"));

        pricingRules.add(pricingRuleA);
        pricingRules.add(pricingRuleB);

        exceptedPricingRules = pricingRules;
    }

    private void givenExceptedEmptyPricingRule()
    {
        exceptedPricingRules = Collections.emptySet();
    }

    private void whenMapCalled()
    {
        actualPricingRules = pricingRuleMapper.map(pricingRequest);
    }

    private void thenItemShouldBeMapped()
    {
        List<PricingRule> actualPricingRuleList = new ArrayList<>(actualPricingRules);
        List<PricingRule> exceptedPricingRuleList = new ArrayList<>(exceptedPricingRules);

        actualPricingRuleList.sort(Comparator.comparing(i -> i.getItemIdentifier().getIdentifier()));
        exceptedPricingRuleList.sort(Comparator.comparing(i -> i.getItemIdentifier().getIdentifier()));

        Assert.assertEquals(actualPricingRules, exceptedPricingRules);
    }

}
