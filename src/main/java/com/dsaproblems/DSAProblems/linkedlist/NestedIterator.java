package com.dsaproblems.DSAProblems.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class NestedInteger {

    private final Integer singleInteger;
    private final ArrayList<NestedInteger> list;

    NestedInteger(int x) {
        this.singleInteger = x;
        this.list = null;
    }

    NestedInteger(ArrayList<NestedInteger> list) {
        this.list = list;
        this.singleInteger = null;
    }

    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger() {
        return singleInteger != null;
    }

    // Return the single integer that this NestedInteger holds, if it holds a single integer.
    // The result is 1e9 if this NestedInteger holds a nested list.
    Integer getInteger() {
        return singleInteger;
    }

    // Return the nested list that this NestedInteger holds, if it holds a nested list.
    // The result is an empty ArrayList if this NestedInteger holds a single integer.
    ArrayList<NestedInteger> getList() {
        return list;
    }
}

public class NestedIterator {

    private final Deque<Integer> queue;

    NestedIterator(ArrayList<NestedInteger> nestedList) {
        this.queue = new ArrayDeque<>();
        flatten(nestedList);
    }

    private void flatten(ArrayList<NestedInteger> nestedList) {
        for (NestedInteger element : nestedList) {
            if (element.isInteger()) queue.addLast(element.getInteger());
            else flatten(element.getList());
        }
    }

    public Integer next() {
        return queue.pollFirst();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
