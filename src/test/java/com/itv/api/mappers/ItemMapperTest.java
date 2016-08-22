package com.itv.api.mappers;

import com.itv.api.model.PricingRequest;
import com.itv.domain.mappers.ItemMapper;
import com.itv.domain.model.Item;
import com.itv.domain.model.ItemIdentifier;
import com.itv.domain.model.UnitPrice;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Test(groups = {"unit"})
public class ItemMapperTest
{
    private ItemMapper itemMapper;
    private PricingRequest pricingRequest;
    private List<Item> actualItems;
    private List<Item> exceptedItems;

    @BeforeMethod
    public void setup()
    {
        itemMapper = null;
        pricingRequest = null;
        actualItems = null;
        exceptedItems = null;
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

        List<com.itv.api.model.Item> items = new ArrayList<>();
        com.itv.api.model.Item itemA = new com.itv.api.model.Item("A", new BigDecimal(50));
        com.itv.api.model.Item itemB = new com.itv.api.model.Item("B", new BigDecimal(30));
        com.itv.api.model.Item itemC = new com.itv.api.model.Item("C", new BigDecimal(20));
        com.itv.api.model.Item itemD = new com.itv.api.model.Item("D", new BigDecimal(15));
        items.add(itemA);
        items.add(itemB);
        items.add(itemB);
        items.add(itemC);
        items.add(itemD);
        items.add(itemD);

        pricingRequest.setItems(items);
    }

    private void givenExceptedItem()
    {
        List<Item> items = new ArrayList<>();

        Item itemA = new Item(new ItemIdentifier("A"), 1, new UnitPrice(new BigDecimal(50)));
        Item itemB = new Item(new ItemIdentifier("B"), 2, new UnitPrice(new BigDecimal(30)));
        Item itemC = new Item(new ItemIdentifier("C"), 1, new UnitPrice(new BigDecimal(20)));
        Item itemD = new Item(new ItemIdentifier("D"), 2, new UnitPrice(new BigDecimal(15)));

        items.add(itemA);
        items.add(itemB);
        items.add(itemC);
        items.add(itemD);

        exceptedItems = items;
    }

    private void whenMapCalled()
    {
        actualItems = itemMapper.map(pricingRequest);
    }

    private void thenItemShouldBeMapped()
    {
        actualItems.sort(Comparator.comparing(i -> i.getItemIdentifier().getIdentifier()));
        exceptedItems.sort(Comparator.comparing(i -> i.getItemIdentifier().getIdentifier()));

        Assert.assertEquals(actualItems, exceptedItems);
    }
}
