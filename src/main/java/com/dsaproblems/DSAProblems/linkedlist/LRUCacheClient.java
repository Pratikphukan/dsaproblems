package com.dsaproblems.DSAProblems.linkedlist;

public class LRUCacheClient {

    public static void main(String[] args) {

        LRUCache<String> cache = new LRUCache<>(5);

        cache.setRecord("pratik", "1");
        cache.setRecord("putoli", "2");
        cache.setRecord("ila", "3");
        System.out.println(cache.getRecord("putoli"));
        cache.setRecord("rana", "4");
        cache.setRecord("biman", "4");
        System.out.println(cache.getRecord("rana"));
        cache.setRecord("runima", "9");
    }

}
