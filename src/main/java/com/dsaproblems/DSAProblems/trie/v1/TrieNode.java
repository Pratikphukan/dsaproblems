package com.dsaproblems.DSAProblems.trie.v1;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {

    Map<T, TrieNode<T>> next;
    boolean isEnd;

    TrieNode(boolean isEnd) {
        this.next = new HashMap<>();
        this.isEnd = isEnd;
    }
}
