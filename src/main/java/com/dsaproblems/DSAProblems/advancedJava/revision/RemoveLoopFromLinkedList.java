package com.dsaproblems.DSAProblems.advancedJava.revision;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class RemoveLoopFromLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode cycleStart = new ListNode(3);
        head.next.next = cycleStart;
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = cycleStart;

        //System.out.println(removeLoopFromLinkedList(head));
        System.out.println(removeLoopFromLinkedListv1(head));
    }

    private static ListNode removeLoopFromLinkedListv1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        // No loop
        if (fast == null || fast.next == null) return head;
        // Find loop start
        slow = head;
        if (slow == fast) {
            // Loop starts at head
            while (fast.next != slow) fast = fast.next;
        } else {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        fast.next = null;
        return head;
    }

    public static ListNode removeLoopFromLinkedList(ListNode A) {
        if (A == null) {
            return null;
        }
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow == fast) {
            if (fast == A) {
                while (slow != null && slow.next != fast) {
                    slow = slow.next;
                }
                slow.next = null;
            } else {
                slow = A;
                while (slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null;
            }
        }
        return A;
    }
}
