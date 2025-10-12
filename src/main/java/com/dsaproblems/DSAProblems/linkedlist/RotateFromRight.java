package com.dsaproblems.DSAProblems.linkedlist;

public class RotateFromRight {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;
        System.out.println(rotateRightv1(head, k));
    }

    private static ListNode rotateRightv1(ListNode head, int k) {
        int count = 0;
        ListNode temp = head;
        ListNode tail = null;
        while (temp != null) {
            count += 1;
            if (temp.next == null)
                tail = temp;
            temp = temp.next;
        }
        int B = count - k;
        ListNode curr = head;
        ListNode prev = null;
        int i = 0;
        while (i < B) {
            prev = curr;
            curr = curr.next;
            i += 1;
        }
        prev.next = null;
        tail.next = head;
        head = curr;
        return head;
    }
}
