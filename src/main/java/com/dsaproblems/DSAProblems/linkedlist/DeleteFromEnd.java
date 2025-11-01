package com.dsaproblems.DSAProblems.linkedlist;

public class DeleteFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
//        head.getNext().setNext(new ListNode(3));
//        head.getNext().getNext().setNext(new ListNode(4));
//        head.getNext().getNext().getNext().setNext(new ListNode(5));
//        head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
        System.out.println(deleteNthNodeFromEndv1(head, 1));
        System.out.println(deleteNthNodeFromEndv2(head, 1));
    }

    //working code
    private static ListNode deleteNthNodeFromEndv2(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy, p2 = dummy;

        // advance p1 by n+1 steps
        for (int i = 0; i <= n; i++) {
            p1 = p1.next;
        }
        // move both until fast reaches the end
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        // slow.next is the node to delete
        p2.next = p2.next.next;
        return dummy.next;
    }

    private static ListNode deleteNthNodeFromEndv1(ListNode head, int n) {
        int len = getLengthOfList(head);
        int pos = len - n;
        if (pos == 1 || pos == 0) return head.next;
        ListNode temp = head;
        for (int i = 1; i < pos; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    private static int getLengthOfList(ListNode head) {
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        return count;
    }
}
