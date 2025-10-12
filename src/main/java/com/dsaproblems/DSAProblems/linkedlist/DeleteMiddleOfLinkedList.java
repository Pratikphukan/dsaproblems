package com.dsaproblems.DSAProblems.linkedlist;

public class DeleteMiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().setNext(new ListNode(5));
        // ListNode modifiedNode = deleteMiddleOfLinekedList(head);
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
        ListNode modifiedNode = deleteMiddleOfLinkedListv2(head);

    }

    //working code
    private static ListNode deleteMiddleOfLinkedListv2(ListNode A) {
        if (A == null || A.next == null) {
            return null;
        }
        ListNode slow = A, fast = A.next, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow.next = slow.next.next;
        else prev.next = slow.next;
        return A;
    }

    private static ListNode deleteMiddleOfLinkedListv1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;
        return head;
    }

}
