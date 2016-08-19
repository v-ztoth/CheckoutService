package com.itv.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ItemIdentifier
{
    private final String identifier;

    public ItemIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }
}
