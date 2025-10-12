package com.dsaproblems.DSAProblems.linkedlist;

public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.setNext(new ListNode(1));
        head.getNext().setNext(new ListNode(3));
        head.getNext().getNext().setNext(new ListNode(3));
        head.getNext().getNext().getNext().setNext(new ListNode(3));
        // ListNode modifiedNode = deleteMiddleOfLinekedList(head);
        head.getNext().getNext().getNext().getNext().setNext(new ListNode(4));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(5));
        //System.out.println(deleteDuplicatesv1(head));
        System.out.println(deleteDuplicatesv2(head));
    }

    //working code, remove all duplicates
    private static ListNode deleteDuplicatesv2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            boolean isDuplicate = false;
            while (curr.next != null && curr.val.equals(curr.next.val)) {
                curr = curr.next;
                isDuplicate = true;
            }
            if (isDuplicate) {
                prev.next = curr.next; // skip all duplicates
            } else {
                prev = prev.next; // move prev forward
            }
            curr = curr.next;
        }
        return dummy.next == null ? null : dummy.next;
    }

    //It runs in O(n) time and O(1) space
    //working code, keep one duplicate
    private static ListNode deleteDuplicatesv1(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            ListNode repeat = temp;
            while (repeat.next != null && repeat.val.equals(repeat.next.val)) {
                repeat = repeat.next;
            }
            temp.next = repeat.next;
            temp = temp.next;
        }
        return head;
    }
}
