package com.dsaproblems.DSAProblems.linkedlist;

public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println(swapNodesInPairsv1(head));
    }

    private static ListNode swapNodesInPairsv1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // perform swap
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // move prev to the end of the swapped pair
            prev = first;
        }

        return dummy.next;
    }
}
