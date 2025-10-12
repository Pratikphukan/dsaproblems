package com.dsaproblems.DSAProblems.heap01;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Item implements Comparable<Item> {

    private int originalValue;

    @Setter
    private int modifiedValue;

    public Item(int originalValue, int modifiedValue) {
        super();
        this.originalValue = originalValue;
        this.modifiedValue = modifiedValue;
    }

    @Override
    public int compareTo(Item item) {
        return (this.originalValue + this.modifiedValue) - (item.getOriginalValue() + item.getModifiedValue());
    }

}
