package com.dsaproblems.DSAProblems.linkedlist;

import lombok.NonNull;

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
