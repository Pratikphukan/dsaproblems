package com.dsaproblems.DSAProblems.advancedJava.revision;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        //head.next.next.next.next.next = new ListNode(6);

        System.out.println(reorderListv1(head));
    }

    private static ListNode reorderListv1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getFirstMiddleNode(head); //Find the middle node using the slow and fast pointer technique
        ListNode h1 = head;
        ListNode h2 = reverseLinkedList(mid.next);//Reverse the second half of the list starting after the middle node
        mid.next = null;
        while (h1 != null && h2 != null) {
            ListNode temp1 = h1;
            ListNode temp2 = h2;
            h1 = h1.next;
            h2 = h2.next;
            temp1.next = temp2;
            temp2.next = h1;
        }
        return head;
    }

    public static ListNode getFirstMiddleNode(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head;
        ListNode h2 = null;
        while (h1 != null) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
        }
        return h2;
    }
}
