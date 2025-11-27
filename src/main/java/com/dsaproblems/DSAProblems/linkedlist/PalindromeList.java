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
        System.out.println(lPalinv1(head));
        System.out.println(lPalinv2(head));
    }

    private static boolean lPalinv2(ListNode head) {
        if (head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = fast != null ? slow.next : slow; //for odd length, skip middle
        ListNode h1 = head, h2 = reverse(second);
        while (h2 != null) {
            if (!h1.val.equals(h2.val)) return false;
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode h1 = head, h2 = null;
        while (h1 != null) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
        }
        return h2;
    }

    public static int lPalinv1(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode slow = head;
        ListNode fast = head.next;
        stack.addFirst(slow);
        while (fast != null && fast.next != null) {
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
