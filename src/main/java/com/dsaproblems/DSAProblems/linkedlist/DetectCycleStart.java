package com.dsaproblems.DSAProblems.linkedlist;

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
        System.out.println(detectCyclev2(head));
    }

    //working code
    private static ListNode detectCyclev2(ListNode A) {
        if (A == null) return null;
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = A;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //uses Floyd's Tortoise and Hare: O(n) time and O(1) extra space
    //working code
    private static ListNode detectCyclev1(ListNode A) {
        if (A == null) return null;
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break; // don't compare the data as same values may be there
        }
        if (slow == fast) {
            if (fast == A) {
                return A;
            }
            slow = A;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast.next;
        }
        return null;
    }
}
