package com.dsaproblems.DSAProblems.linkedlist;

public class LinkedListClient {

	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		head.setNext(new ListNode(5));
		head.getNext().setNext(new ListNode(8));

		System.out.println(LinkedListFactory.size(head));

		head = LinkedListFactory.insertAtFront(head, 7);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.insertAtEnd(head, 43);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.insertAtKthPosition(head, 3, 76);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.insertAtKthPosition(head, 0, 76);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.insertAtKthPosition(head, LinkedListFactory.size(head), 76);
		LinkedListFactory.printList(head);

		ListNode head1 = new ListNode(3);
		head1 = LinkedListFactory.insertAtEnd(head1, 8);
		head1 = LinkedListFactory.insertAtEnd(head1, 10);
		head1 = LinkedListFactory.insertAtEnd(head1, 14);
		head1 = LinkedListFactory.insertAtEnd(head1, 20);
		LinkedListFactory.printList(head1);
		LinkedListFactory.insertInSortedOrder(head1, 5);
		LinkedListFactory.printList(head1);
		LinkedListFactory.insertInSortedOrder(head1, 34);
		LinkedListFactory.printList(head1);

		head = LinkedListFactory.reverseLinkedList(head);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.reverseFirstKNodes(head, 3);
		LinkedListFactory.printList(head);

		head = LinkedListFactory.reverseFirstKNodes(head, LinkedListFactory.size(head));
		LinkedListFactory.printList(head);

		head = LinkedListFactory.reverseSublistsOfSizeK(head, 3);
		LinkedListFactory.printList(head);

		ListNode middleNode = LinkedListFactory.getFirstMiddleNode(head);
		System.out.println(middleNode);

		middleNode = LinkedListFactory.getSecondMiddleNode(head);
		System.out.println(middleNode);

		middleNode = LinkedListFactory.getFirstMiddleNode(head1);
		System.out.println(middleNode);

		middleNode = LinkedListFactory.getSecondMiddleNode(head1);
		System.out.println(middleNode);

		head = LinkedListFactory.sortLinkedList(head);
		LinkedListFactory.printList(head);

		head1 = LinkedListFactory.reverseLinkedList(head1);
		LinkedListFactory.printList(head1);

		head1 = LinkedListFactory.sortLinkedList(head1);
		LinkedListFactory.printList(head1);

		ListNode root1 = LinkedListFactory.insertAtEnd(null, 3);
		root1 = LinkedListFactory.insertAtEnd(root1, 8);
		root1 = LinkedListFactory.insertAtEnd(root1, 10);
		root1 = LinkedListFactory.insertAtEnd(root1, 14);
		root1 = LinkedListFactory.insertAtEnd(root1, 20);
		LinkedListFactory.printList(root1);

		ListNode root2 = LinkedListFactory.insertAtEnd(null, 2);
		root2 = LinkedListFactory.insertAtEnd(root2, 6);
		root2 = LinkedListFactory.insertAtEnd(root2, 11);
		root2 = LinkedListFactory.insertAtEnd(root2, 12);
		LinkedListFactory.printList(root2);

		ListNode sortedRoot = LinkedListFactory.reverseMergeSortedLinkedLists(root1, root2);
		LinkedListFactory.printList(sortedRoot);

		System.out.println(LinkedListFactory.isLoopPresent(sortedRoot));

		System.out.println(LinkedListFactory.getLengthOfLoop(sortedRoot));

		//LinkedListFactory.removeLoopFromLinkedList(sortedRoot);
	}

}
