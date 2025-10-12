package com.dsaproblems.DSAProblems.stack01;

public class LinkedListStack implements Stack {

	private ListNode head = null;

	@Override
	public void push(Integer data) {
		if (head == null) {
			head = new ListNode(data);
			return;
		}
		ListNode temp = new ListNode(data);
		temp.setNext(head);
		head = temp;
	}

	@Override
	public Integer pop() {
		return null;
	}

	@Override
	public Integer peek() {
		return null;
	}

}
