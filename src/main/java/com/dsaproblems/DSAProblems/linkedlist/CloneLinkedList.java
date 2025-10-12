package com.dsaproblems.DSAProblems.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedList {

    static class ListNode {
        int val; // value stored in the node
        ListNode next; // pointer to the next node in the list
        ListNode random; // pointer to any node in the list (or null)

        // Constructor to initialize the node with its value.
        ListNode(int x) {
            val = x;      // set node's value
            next = null;  // default next pointer is null
            random = null; // default random pointer is null
        }
    }

    public static void main(String[] args) {

        ListNode node4a = new ListNode(10);
        ListNode node4b = new ListNode(20);
        ListNode node4c = new ListNode(30);
        ListNode node4d = new ListNode(40);
        node4a.next = node4b; // linking nodes
        node4b.next = node4c;
        node4c.next = node4d;

        // Setting random pointers arbitrarily.
        node4a.random = node4c; // first random to third node
        node4b.random = node4a; // second random to first node
        node4c.random = node4d; // third random to fourth node
        node4d.random = node4b; // fourth random to second node


        System.out.println(cloneListv1(node4a));
    }

    private static ListNode cloneListv1(ListNode A) {
        if (A == null) return null;
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode current = A;
        while (current != null) {
            map.put(current, new ListNode(current.val));
            current = current.next;
        }
        current = A;
        while (current != null) {
            // Set the cloned node's next pointer by fetching from the map (if exists).
            map.get(current).next = (current.next != null) ? map.get(current.next) : null;
            // Set the cloned node's random pointer by fetching from the map (if exists).
            map.get(current).random = (current.random != null) ? map.get(current.random) : null;
            current = current.next;
        }
        return map.get(A);
    }
}
