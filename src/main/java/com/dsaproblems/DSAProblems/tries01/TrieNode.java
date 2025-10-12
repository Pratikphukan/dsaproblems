package com.dsaproblems.DSAProblems.tries01;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {

    private Map<T, TrieNode<T>> node;

    private boolean isEnd;

    public TrieNode(boolean isEnd) {
        this.node = new HashMap<>();
        this.isEnd = isEnd;
    }

    public Map<T, TrieNode<T>> getNode() {
        return node;
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "node -> " + node +
                ", isEnd=" + isEnd +
                '}';
    }
}
