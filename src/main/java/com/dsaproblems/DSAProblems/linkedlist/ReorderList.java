package com.dsaproblems.DSAProblems.linkedlist;

public class ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println(reorderListv1(head));
    }

    //getFirstMiddleNode(head) - Uses slow/fast pointer technique to find the middle node
    //Traverses half the list → O(n/2) = O(n)
    //reverseLinkedList(mid.next) - Reverses the second half of the list
    //Traverses half the list → O(n/2) = O(n)
    //Merging loop - Interleaves nodes from both halves
    //Each node is visited once → O(n/2) = O(n)
    //TC: O(n), SC: O(1)
    private static ListNode reorderListv1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find the middle node
        ListNode mid = getFirstMiddleNode(head);

        // Step 2: Reverse the second half
        ListNode secondHalf = reverseLinkedList(mid.next);
        mid.next = null; // Split the list into two halves

        // Step 3: Merge the two halves alternately
        ListNode firstHalf = head;
        while (secondHalf != null) { //the second half is always shorter or equal in length
            ListNode nextFirst = firstHalf.next;
            ListNode nextSecond = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = nextFirst;

            firstHalf = nextFirst;
            secondHalf = nextSecond;
        }

        return head;
    }

    public static ListNode getFirstMiddleNode(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head;
        ListNode h2 = null;
        while (h1 != null) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
        }
        return h2;
    }
}
