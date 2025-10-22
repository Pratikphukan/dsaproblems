package com.dsaproblems.DSAProblems.linkedlist;

public class ReverseFromBC {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        //head.next.next.next.next.next = new ListNode(6);
        System.out.println(reverseFromBCv2(head, 3, 5));

    }

    //working code
    //Loop Body Execution->Update->Condition Check->...
    private static ListNode reverseFromBCv2(ListNode A, int B, int C) {
        if (A == null || B == C) return A;
        ListNode dummy = new ListNode(0);
        dummy.next = A;
        ListNode prev = dummy;
        for (int i = 1; i < B; i++) {
            prev = prev.next;
        }
        ListNode h1 = prev.next, h2 = null, last = prev.next;
        for (int i = 0; i <= C - B; i++) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
        }
        last.next = h1;
        prev.next = h2;
        return dummy.next;
    }

    private static ListNode reverseFromBCv1(ListNode head, int B, int C) {
        int i = 1;
        ListNode curr = head, prev = null;
        while (i < B) {
            prev = curr;
            curr = curr.next;
            i++;
        }
        if (prev != null) {
            prev.next = reverseList(curr, C - B);
            return head;
        }
        return reverseList(curr, C - B);
    }

    private static ListNode reverseList(ListNode curr, int count) {
        if (curr == null || count == 0) {
            return curr;
        }
        ListNode h1 = curr, h2 = null;
        while (count >= 0) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
            count--;
        }
        curr.next = h1;
        return h2;
    }
}
