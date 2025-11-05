package com.dsaproblems.DSAProblems.linkedlist;

public class RemoveLLElements {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
        head.getNext().setNext(new ListNode(6));
        head.getNext().getNext().setNext(new ListNode(6));
        head.getNext().getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(5));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(6));
        System.out.println(removeLLElementsv1(head, 6));
        System.out.println(removeLLElementsv2(head, 6));
    }

    //working code
    private static ListNode removeLLElementsv2(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    //Time: O(n) — visits each node once
    //Space: O(1) extra
    //working code
    private static ListNode removeLLElementsv1(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head, prev = dummy;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr = prev.next;
                continue;
            }
            prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}
