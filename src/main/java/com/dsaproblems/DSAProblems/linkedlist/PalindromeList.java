package com.dsaproblems.DSAProblems.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

public class PalindromeList {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.setNext(new ListNode(4));
        head.getNext().setNext(new ListNode(5));
        head.getNext().getNext().setNext(new ListNode(5));
        head.getNext().getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(3));
        System.out.println(lPalin(head));

    }

    public static int lPalin(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode slow = head;
        ListNode fast = head.next;
        stack.addFirst(slow);
        while (fast != null && fast.getNext() != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.addFirst(slow);
        }
        if (fast == null) { //odd number of nodes
            stack.removeFirst();
        }
        ListNode half = slow.next;
        while (half != null) {
            if (!stack.isEmpty() && !stack.peekFirst().val.equals(half.val)) {
                return 0;
            }
            stack.removeFirst();
            half = half.next;
        }
        return 1;
    }

}
