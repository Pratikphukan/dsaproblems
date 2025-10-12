package com.dsaproblems.DSAProblems.linkedlist;

public class KReverseLL {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        int B = 2;

//        System.out.println(kReverseLLv1(head, B));
//        System.out.println(kGroupReverseLLv1(head, B));
        System.out.println(kGroupReverseLLv2(head, B));
    }

    //not working, need to fix it
    private static ListNode kGroupReverseLLv2(ListNode A, int B) {
        if (B < 2 || A == null || A.next == null) {
            return A;
        }
        int k = Math.min(B, size(A));
        ListNode curr = A, head = null, prev = null;
        while (curr != null) {
            ListNode h1 = curr, h2 = null;
            int i = 0;
            while (h1 != null && i < k) {
                ListNode temp = h1;
                h1 = h1.next;
                temp.next = h2;
                h2 = temp;
                i++;
            }
            if (prev == null) {
                head = h2;
                prev = head;
            } else
                prev.next = h2;
            curr.next = h1;
            curr = curr.next;
        }
        return head;
    }

    private static ListNode kGroupReverseLLv1(ListNode head, int B) {
        if (B < 2 || head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head, h2 = null;
        int k = Math.min(B, size(head));
        int i = 0;
        while (h1 != null && i < k) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
            i++;
        }
        if (h1 != null) {
            head.next = kGroupReverseLLv1(h1, k);
        }
        return h2;
    }

    private static ListNode kReverseLLv1(ListNode head, int B) {
        if (B < 2 || head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head, h2 = null;
        int k = Math.min(B, size(head));
        while (h1 != null && k > 0) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
            k--;
        }
        head.next = h1;
        return h2;
    }

    public static int size(ListNode node) {
        ListNode temp = node;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
