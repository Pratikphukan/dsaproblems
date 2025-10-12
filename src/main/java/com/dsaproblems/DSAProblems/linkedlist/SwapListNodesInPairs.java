package com.dsaproblems.DSAProblems.linkedlist;

public class SwapListNodesInPairs {

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.setNext(new ListNode(2));
		head.getNext().setNext(new ListNode(3));
		head.getNext().getNext().setNext(new ListNode(4));
		head.getNext().getNext().getNext().setNext(new ListNode(5));
		head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
		System.out.println(swapPairs(head));

	}

	private static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode h1 = head;
		ListNode h2 = null;
		ListNode temp = null;
		int i = 0;
		while (h1 != null && i < 2) {
			temp = h1;
			h1 = h1.next;
			temp.next = h2;
			h2 = temp;
			i++;
		}
		if (h1 != null) {
			head.next = swapPairs(h1);
		}
		return h2;
	}

}
