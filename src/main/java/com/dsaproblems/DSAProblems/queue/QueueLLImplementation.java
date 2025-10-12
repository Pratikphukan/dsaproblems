package com.dsaproblems.DSAProblems.queue;

class ListNode {

	int data;
	ListNode next;

	public ListNode(int data) {
		this.data = data;
		this.next = null;
	}

}

class QueueLinked {

	ListNode front = null;
	ListNode rear = null;

	public void push(int val) { // insert at the end
		if (front == null) {
			front = rear = new ListNode(val);
			return;
		}
		rear.next = new ListNode(val);
		rear = rear.next;
	}

	public int pop() { // remove from the head
		if (front == null) {
			System.out.println("Queue is empty");
			return Integer.MIN_VALUE;
		}
		int val = front.data;
		front = front.next;
		if (front == null) {
			rear = null;
		}
		return val;
	}

	public int getFront() {
		if (front == null) {
			System.out.println("Queue is empty");
			return Integer.MIN_VALUE;
		}
		return front.data;
	}

	public int getRear() {
		if (rear == null) {
			System.out.println("Queue is empty");
			return Integer.MIN_VALUE;
		}
		return rear.data;
	}

}

public class QueueLLImplementation {

	public static void main(String[] args) {
		QueueLinked stack = new QueueLinked();
		stack.push(12);
		stack.push(5);
		stack.push(34);
		stack.push(42);
		stack.push(91);
		stack.push(9);
		System.out.println(stack.pop());
		stack.push(39);
		System.out.println(stack.getFront());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.getRear());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
