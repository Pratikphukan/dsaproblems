package com.dsaproblems.DSAProblems.linkedlist;

public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(2));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().setNext(new ListNode(5));
        // ListNode modifiedNode = deleteMiddleOfLinekedList(head);
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
        System.out.println(rearrangeLinkedListv1(head));
    }

    private static ListNode rearrangeLinkedListv1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // To connect at the end of odd list
        while (even != null && even.next != null) {
            odd.next = even.next; // Link odd to the next odd node
            odd = odd.next; // Move odd pointer
            even.next = odd.next; // Link even to the next even node
            even = even.next; // Move even pointer
        }
        odd.next = evenHead; // Connect the end of odd list to the head of even list
        return head;
    }
}
