package com.dsaproblems.DSAProblems.intermediate;

import com.dsaproblems.DSAProblems.linkedlist.ListNode;

public class LinkedListFactory {

    public ListNode insertAtFront(ListNode head, int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        return newNode;
    }

    public ListNode insertAtLast(ListNode head, int data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            return newNode;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    public ListNode insertAtGivenPosition(ListNode head, int data, int pos) {
        ListNode newNode = new ListNode(data);
        if (pos == 0) {
            newNode.next = head;
            return newNode;
        }
        ListNode temp = head;
        int count = 0;
        while (count < pos - 1 && temp != null) {
            temp = temp.next;
            count++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    private int getLengthOfList(ListNode head) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public ListNode removeFromKthPosition(ListNode head, int pos) {
        ListNode removedNode = null;
        if (head != null) {
            if (pos == 0) {
                removedNode = head;
                head = head.next;
            } else if (pos > 0 || pos < getLengthOfList(head)) {
                ListNode temp = head;
                int count = 0;
                while (count < (pos - 1) && temp != null) {
                    temp = temp.next;
                    count++;
                }
                removedNode = temp.next;
                temp.next = removedNode.next;
            }
            removedNode.next = null;
        }
        return head;
    }
}
