package com.dsaproblems.DSAProblems.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<T> {

    class Node {
        String key;
        T value;
        Node prev, next;

        Node(String key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;

    private final Node head, tail;

    private final Map<String, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void setRecord(String key, T value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToTail(node);
        } else {
            if (map.size() == capacity) {
                Node lru = head.next;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addToTail(newNode);
            map.put(key, newNode);
        }
    }

    private void addToTail(Node newNode) {
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public T getRecord(String key) {
        Node node = map.get(key);
        if (node == null) return null;
        moveToTail(node);
        return node.value;
    }

    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }
}
