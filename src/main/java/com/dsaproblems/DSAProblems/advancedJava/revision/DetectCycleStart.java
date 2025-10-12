package com.dsaproblems.DSAProblems.advancedJava.revision;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class DetectCycleStart {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode cycleStart = new ListNode(3);
        head.next.next = cycleStart;
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = cycleStart;

        System.out.println(detectCyclev1(head));
    }

    private static ListNode detectCyclev1(ListNode A) {
        if (A == null) {
            return A;
        }
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // don't compare the data as same values may be there
                break;
            }
        }
        ListNode ans = null;
        if (slow == fast) {
            if (fast == A) {
                ans = A;
            } else {
                slow = A;
                while (slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                ans = fast.next;
            }
        }
        return ans;
    }
}
