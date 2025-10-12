package com.dsaproblems.DSAProblems.linkedlist;

public class SortList {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(5);

        ListNode x = sortListv1(head);
        System.out.println(x);
    }

    //working code
    //TC: O(n log n)
    //SC: O(log n) (recursion stack only)
    private static ListNode sortListv1(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode mid = getFirstMiddleNode(A);
        ListNode h2 = mid.next;
        mid.next = null;
        //return mergeSortv1(sortListv1(A), sortListv1(h2));
        return mergeSortv2(sortListv1(A), sortListv1(h2));
    }

    private static ListNode mergeSortv2(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                tail.next = h1;
                h1 = h1.next;
            } else {
                tail.next = h2;
                h2 = h2.next;
            }
            tail = tail.next;
        }
        tail.next = (h1 != null) ? h1 : h2;
        return dummy.next;
    }

    private static ListNode mergeSortv1(ListNode h1, ListNode h2) {
        ListNode head = null, temp = null;
        while (h1 != null && h2 != null) {
            if (h1.val > h2.val) {
                if (head == null) {
                    head = h2;
                    temp = head;
                } else {
                    temp.next = h2;
                    temp = temp.next;
                }
                h2 = h2.next;
            } else {
                if (head == null) {
                    head = h1;
                    temp = head;
                } else {
                    temp.next = h1;
                    temp = temp.next;
                }
                h1 = h1.next;
            }
        }
        if (h2 == null) temp.next = h1;
        if (h1 == null) temp.next = h2;
        return head;
    }

    private static ListNode getFirstMiddleNode(ListNode A) {
        ListNode slow = A, fast = A.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
