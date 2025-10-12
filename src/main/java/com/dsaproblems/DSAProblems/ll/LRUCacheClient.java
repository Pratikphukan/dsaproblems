package com.dsaproblems.DSAProblems.ll;


public class LRUCacheClient {

    public static void main(String[] args) {
        LRUCache<Integer> cache = new LRUCache<>(4);
        cache.setRecord("key1", 1);
        cache.setRecord("key2", 2);
        System.out.println(cache.getRecord("key1"));
    }
}
