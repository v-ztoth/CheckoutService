package domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class CalculatedPrice
{
    private final Long price;

    public CalculatedPrice(Long price)
    {
        this.price = price;
    }

    public Long getPrice()
    {
        return price;
    }
}
