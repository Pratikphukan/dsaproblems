package com.dsaproblems.DSAProblems.stack;

public class LinkedListStack implements Stack {

	ListNode node = null;

	@Override
	public void push(Integer val) {
		if (node == null) {
			node = new ListNode(val);
			return;
		}
		ListNode temp = new ListNode(val);
		temp.next = node;
		node = temp;
	}

	@Override
	public Integer pop() {
		if (node == null) {
			System.out.println("Stack is empty");
			return Integer.MIN_VALUE;
		}
		int val = node.data;
		node = node.next;
		return val;
	}

	@Override
	public Integer peek() {
		if (node == null) {
			System.out.println("Stack is empty");
			return Integer.MIN_VALUE;
		}
		return node.data;
	}

}
