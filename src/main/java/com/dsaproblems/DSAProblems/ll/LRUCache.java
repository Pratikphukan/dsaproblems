package com.dsaproblems.DSAProblems.ll;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LRUCache<T> {

    private Integer capacity;

    private Deque<String> queue;

    private ConcurrentMap<String, T> map;

    public LRUCache(Integer capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.map = new ConcurrentHashMap<>();
    }

    public void setRecord(String key, T item) {
        if (!map.containsKey(key) && queue.size() == capacity) {
            String leastUsedItem = queue.pollFirst(); //remove the least recently used item
            map.remove(leastUsedItem);
        } else {
            if (map.containsKey(key)) {
                queue.remove(key); //remove the first occurrence of the existing item from the queue, this will be O(n) operation
            }
        }
        queue.offerLast(key);
        map.put(key, item); //add the new item to the queue and map
    }

    public T getRecord(String key) {
        if (map.containsKey(key)) {
            T value = map.get(key);
            queue.remove(key); //remove the item from the queue, this will be O(n) operation
            queue.offerLast(key); //add it back to the end of the queue
            return value; //indicating that the item was found
        }
        return null; //indicating that the item was not found
    }
}
