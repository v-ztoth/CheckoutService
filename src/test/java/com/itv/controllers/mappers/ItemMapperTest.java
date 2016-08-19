package com.itv.controllers.mappers;

import com.itv.controllers.model.PricingRequest;
import com.itv.domain.mappers.ItemMapper;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"unit"})
public class ItemMapperTest
{
    private ItemMapper itemMapper;
    private PricingRequest pricingRequest;
    private Item actualItem;
    private Item exceptedItem;

    @BeforeMethod
    public void setup()
    {
        itemMapper = null;
        pricingRequest = null;
        actualItem = null;
        exceptedItem = null;
    }

    @Test
    public void mapTest()
    {
        givenAnItemMapper();
        givenAPricingRequest();
        givenExceptedItem();

        whenMapCalled();

        thenItemShouldBeMapped();
    }

    private void givenAnItemMapper()
    {
        itemMapper = new ItemMapper();
    }

    private void givenAPricingRequest()
    {
        pricingRequest = new PricingRequest();
        pricingRequest.setItemCount(3);
        pricingRequest.setItemIdentifier("A");
    }

    private void givenExceptedItem()
    {
        exceptedItem = new Item(new ItemIdentifier("A"), 3);
    }

    private void whenMapCalled()
    {
        actualItem = itemMapper.map(pricingRequest);
    }

    private void thenItemShouldBeMapped()
    {
        Assert.assertEquals(actualItem, exceptedItem);
    }
}
