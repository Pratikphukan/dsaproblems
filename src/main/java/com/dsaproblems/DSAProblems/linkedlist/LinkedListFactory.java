package com.dsaproblems.DSAProblems.linkedlist;

public class LinkedListFactory {

	public static void printList(ListNode node) {
		while (node != null) {
			System.out.print(node.getVal() + "->");
			node = node.getNext();
		}
		System.out.println();
	}

	public static Integer size(ListNode node) {
		ListNode temp = node;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}

	public static ListNode insertAtFront(ListNode node, int data) {
		ListNode newNode = new ListNode(data);
		if (node == null) {
			return newNode;
		}
		newNode.setNext(node);
		return newNode;
	}

	public static ListNode insertAtEnd(ListNode node, int data) {
		ListNode newNode = new ListNode(data);
		if (node == null) {
			return newNode;
		}
		ListNode temp = node;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		temp.setNext(newNode);
		return node;
	}

	public static ListNode insertAtKthPosition(ListNode node, int idx, int data) {
		if (node == null) {
			if (idx == 0) {
				return new ListNode(data);
			}
		} else {
			if (idx >= 0 && idx <= size(node)) {
				if (idx == 0) {
					ListNode newNode = new ListNode(data);
					newNode.setNext(node);
					return newNode;
				}
				ListNode temp = node;
				for (int i = 0; i < idx - 1; i++) {
					temp = temp.getNext();
				}
				ListNode newNode = new ListNode(data);
				newNode.setNext(temp.getNext());
				temp.setNext(newNode);
				return node;
			}
		}
		return node;
	}

	public static ListNode insertInSortedOrder(ListNode node, int data) {
		ListNode newNode = new ListNode(data);
		if (node == null) {
			return newNode;
		}
		if (node.getVal() >= data) {
			newNode.setNext(node);
			return newNode;
		}
		ListNode temp = node;
		while (temp.getNext() != null && temp.getNext().getVal() < data) {
			temp = temp.getNext();
		}
		newNode.setNext(temp.getNext());
		temp.setNext(newNode);
		return node;
	}

	public static ListNode reverseLinkedList(ListNode node) {
		if (node == null || node.getNext() == null) {
			return node;
		}
		ListNode h1 = node;
		ListNode h2 = null;
		ListNode temp = null;
		while (h1 != null) {
			temp = h1;
			h1 = h1.getNext();
			temp.setNext(h2);
			h2 = temp;
		}
		return h2;
	}

	public static ListNode reverseFirstKNodes(ListNode node, int k) {
		if (k < 2 || node == null || node.getNext() == null) {
			return node;
		}
		ListNode h1 = node;
		ListNode h2 = null;
		ListNode temp = null;
		if (k > size(node)) {
			k = size(node);
		}
		while (h1 != null && k > 0) {
			temp = h1;
			h1 = h1.getNext();
			temp.setNext(h2);
			h2 = temp;
			k--;
		}
		node.setNext(h1);
		return h2;
	}

	public static ListNode reverseSublistsOfSizeK(ListNode node, int k) {
		if (k < 2 || node == null || node.getNext() == null) {
			return node;
		}
		ListNode h1 = node;
		ListNode h2 = null;
		ListNode temp = null;
		if (k > size(node)) {
			k = size(node);
		}
		int i = 0;
		while (h1 != null && i < k) {
			temp = h1;
			h1 = h1.getNext();
			temp.setNext(h2);
			h2 = temp;
			i++;
		}
		if (h1 != null) {
			node.setNext(reverseSublistsOfSizeK(h1, k));
		}
		return h2;
	}

	public static ListNode getFirstMiddleNode(ListNode node) {
		if (node == null) {
			return node;
		}
		ListNode slow = node;
		ListNode fast = node.getNext();
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;
	}

	public static ListNode getSecondMiddleNode(ListNode node) {
		if (node == null) {
			return node;
		}
		ListNode slow = node;
		ListNode fast = node;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;
	}

	public static ListNode mergeSortedLinkedLists(ListNode h1, ListNode h2) { // h1 and h2 are sorted in ascending order
																				// and head is sorted in ascending order
		if (h1 == null && h2 == null) {
			return null;
		}
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		ListNode head = null;
		if (h1.getVal() > h2.getVal()) {
			head = h2;
			h2 = h2.getNext();
		} else {
			head = h1;
			h1 = h1.getNext();
		}
		ListNode temp = head;
		while (h1 != null && h2 != null) {
			if (h1.getVal() > h2.getVal()) {
				temp.setNext(h2);
				h2 = h2.getNext();
			} else {
				temp.setNext(h1);
				h1 = h1.getNext();
			}
			temp = temp.getNext();
		}
		if (h1 == null) {
			temp.setNext(h2);
		}
		if (h2 == null) {
			temp.setNext(h1);
		}
		return head;
	}

	// h1 and h2 are sorted in ascending order
	// and head is sorted in descending order
	public static ListNode reverseMergeSortedLinkedLists(ListNode h1, ListNode h2) {
		if (h1 == null && h2 == null) {
			return null;
		}
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		ListNode head = null;
		if (h1.getVal() > h2.getVal()) {
			head = h2;
			h2 = h2.getNext();
		} else {
			head = h1;
			h1 = h1.getNext();
		}
		ListNode temp = null;
		while (h1 != null && h2 != null) {
			if (h1.getVal() > h2.getVal()) {
				temp = h2;
				h2 = h2.getNext();
			} else {
				temp = h1;
				h1 = h1.getNext();

			}
			temp.setNext(head);
			head = temp;
		}
		if (h1 == null) {
			temp.setNext(h2);
		}
		if (h2 == null) {
			temp.setNext(h1);
		}
		return head;
	}

	public static ListNode sortLinkedList(ListNode node) {
		if (node == null || node.getNext() == null) {
			return node;
		}
		ListNode mid = getFirstMiddleNode(node);
		ListNode h1 = null;
		ListNode h2 = null;
		h2 = mid.getNext();
		mid.setNext(null);
		h1 = sortLinkedList(node);
		h2 = sortLinkedList(h2);
		return mergeSortedLinkedLists(h1, h2);
	}

	public static boolean isLoopPresent(ListNode node) {
		if (node == null) {
			return false;
		}
		ListNode slow = node;
		ListNode fast = node;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static int getLengthOfLoop(ListNode node) {
		if (node == null) {
			return 0;
		}
		ListNode slow = node;
		ListNode fast = node;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				break;
			}
		}
		int length = 0;
		if (slow == fast) {
			while (slow.getNext() != fast) {
				slow = slow.getNext();
				length++;
			}
			length++;
		}
		return length;
	}

}
