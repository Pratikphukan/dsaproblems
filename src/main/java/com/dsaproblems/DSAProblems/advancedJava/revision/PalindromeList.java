package com.dsaproblems.DSAProblems.advancedJava.revision;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class PalindromeList {

    public static void main(String[] args) {
//        ListNode head = new ListNode(3);
//        head.setNext(new ListNode(4));
//        head.getNext().setNext(new ListNode(5));
//        head.getNext().getNext().setNext(new ListNode(5));
//        head.getNext().getNext().getNext().setNext(new ListNode(4));
//        head.getNext().getNext().getNext().getNext().setNext(new ListNode(3));

        ListNode head = new ListNode(3);
        head.setNext(new ListNode(4));
        head.getNext().setNext(new ListNode(5));
        head.getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().setNext(new ListNode(3));
        System.out.println(lPalinv1(head));
        System.out.println(lPalinv2(head));
        System.out.println(lPalinv3(head));
    }

    private static int lPalinv3(ListNode head) {
        if (head == null || head.next == null) return 1;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = prev;
            prev = temp;
        }
        ListNode left = head, right = prev;
        while (right != null) {
            if (!left.val.equals(right.val)) return 0;
            left = left.next;
            right = right.next;
        }
        return 1;
    }

    //this uses O(n) time and O(n) space due to the stack
    private static int lPalinv2(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode slow = head, fast = head;
        stack.addFirst(slow);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.addFirst(slow);
        }
        ListNode half = fast == null ? stack.removeFirst() : stack.removeFirst().next;
        while (half != null) {
            if (!stack.isEmpty() && !stack.peekFirst().val.equals(half.val)) {
                return 0;
            }
            stack.removeFirst();
            half = half.next;
        }
        return 1;
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
