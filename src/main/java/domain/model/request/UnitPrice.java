package domain.model.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UnitPrice
{
    private final Long price;

    public UnitPrice(Long price)
    {
        this.price = price;
    }

    public Long getPrice()
    {
        return price;
    }
}
