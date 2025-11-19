package com.dsaproblems.DSAProblems.linkedlist;


public class MiddleElementOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().setNext(new ListNode(5));
        // ListNode modifiedNode = deleteMiddleOfLinekedList(head);
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
        System.out.println(findMiddleOfLinkedList(head));

        System.out.println(findFirstMiddleOfLinkedList(head));
        System.out.println(findSecondMiddleOfLinkedList(head));
    }

    public static ListNode findSecondMiddleOfLinkedList(ListNode node) {
        if (node == null) return node;
        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode findFirstMiddleOfLinkedList(ListNode node) {
        if (node == null) return node;
        ListNode slow = node, fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Integer findMiddleOfLinkedList(ListNode head) {
        if (head.next == null) {
            return head.val;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

}
