package com.dsaproblems.DSAProblems.linkedlist;

public class RemoveCycleFromList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode cycleStart = new ListNode(3);
        head.next.next = cycleStart;
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = cycleStart;

        System.out.println(removeCyclev1(head));
    }

    //working code
    private static ListNode removeCyclev1(ListNode A) {
        if (A == null) {
            return null;
        }
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // don't compare the data as same values may be there
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
