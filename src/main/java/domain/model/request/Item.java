package domain.model.request;

public class Item
{
    private final ItemIdentifier itemIdentifier;

    private final UnitPrice unitUnitPrice;

    private final Integer count;

    public Item(ItemIdentifier itemIdentifier, UnitPrice unitUnitPrice, Integer count)
    {
        this.itemIdentifier = itemIdentifier;
        this.unitUnitPrice = unitUnitPrice;
        this.count = count;
    }

    public ItemIdentifier getItemIdentifier()
    {
        return itemIdentifier;
    }

    public UnitPrice getUnitUnitPrice()
    {
        return unitUnitPrice;
    }

    public Integer getCount()
    {
        return count;
    }
}
