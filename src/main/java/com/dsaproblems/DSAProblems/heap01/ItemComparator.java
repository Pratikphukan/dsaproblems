package com.dsaproblems.DSAProblems.heap01;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return (item1.getOriginalValue() + item1.getModifiedValue()) -
                (item2.getOriginalValue() + item2.getModifiedValue());
    }
}
