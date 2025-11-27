package com.dsaproblems.DSAProblems.leetcode;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class IntersectionOfLL {

    public static void main(String[] args) {
        ListNode common = new ListNode(8); // Intersection node
        common.next = new ListNode(4); // Next node in common part
        common.next.next = new ListNode(5); // Last node in common part

        // Create first list: 4 -> 1 -> common
        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = common; // Joining the common part

        // Create second list: 5 -> 6 -> 1 -> common
        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = common; // Joining the common part

        // Get intersection node for Test Case 1
        System.out.println(getIntersectionNodev1(headA1, headB1));
    }

    private static ListNode getIntersectionNodev1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode h1 = headA, h2 = headB;
        while (h1 != h2) {
            h1 = h1 != null ? h1.next : headB;
            h2 = h2 != null ? h2.next : headA;
        }
        return h1;
    }
}
