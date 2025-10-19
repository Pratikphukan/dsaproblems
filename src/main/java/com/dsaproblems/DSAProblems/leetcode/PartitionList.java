package com.dsaproblems.DSAProblems.leetcode;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class PartitionList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(4));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(5));
        head.getNext().getNext().getNext().setNext(new ListNode(2));
        // ListNode modifiedNode = deleteMiddleOfLinekedList(head);
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(1));
        int x = 3;
        //System.out.println(partitionListv1(head, x));
        System.out.println(partitionListv2(head, x));
    }

    //working and optimal
    private static ListNode partitionListv2(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode smallDummy = new ListNode(0), largeDummy = new ListNode(0);
        ListNode small = smallDummy, large = largeDummy;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                small.next = curr;
                small = small.next;
            } else {
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }
        large.next = null; // terminate the large list
        small.next = largeDummy.next; // connect small and large lists
        return smallDummy.next;
    }

    private static ListNode partitionListv1(ListNode head, int x) {
        ListNode curr = head;
        ListNode sd = new ListNode(0);
        ListNode gd = new ListNode(0);
        ListNode s = sd, g = gd;
        while (curr != null) {
            if (curr.val < x) {
                s.next = curr;
                s = s.next;
                if (curr.next == null) g.next = null;
            } else {
                g.next = curr;
                g = g.next;
                if (curr.next == null) s.next = null;
            }
            curr = curr.next;
        }
        ListNode root = sd.next;
        curr = root;
        while (curr != null && curr.next != null) {
            curr = curr.next;
        }
        curr.next = gd.next;
        return root;
    }
}
