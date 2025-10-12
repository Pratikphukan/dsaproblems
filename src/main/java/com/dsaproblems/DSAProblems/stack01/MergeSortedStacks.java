package com.dsaproblems.DSAProblems.stack01;

import java.util.Deque;
import java.util.LinkedList;

public class MergeSortedStacks {

	public static void main(String[] args) {
		Deque<Integer> stack1 = new LinkedList<>();
		stack1.addFirst(3);
		stack1.addFirst(5);
		stack1.addFirst(10);
		stack1.addFirst(15);
		System.out.println(stack1);
		Deque<Integer> stack2 = new LinkedList<>();
		stack2.addFirst(2);
		stack2.addFirst(4);
		stack2.addFirst(6);
		stack2.addFirst(7);
		stack2.addFirst(9);
		System.out.println(stack2);

		System.out.println(mergeSortedStacksv1(stack1, stack2));
	}

	private static Deque<Integer> mergeSortedStacksv1(Deque<Integer> stack1, Deque<Integer> stack2) {
		Deque<Integer> stack = new LinkedList<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			if (stack1.peekFirst() > stack2.peekFirst()) {
				stack.addFirst(stack1.pollFirst());
			} else {
				stack.addFirst(stack2.pollFirst());
			}
		}
		while (!stack1.isEmpty()) {
			stack.addFirst(stack1.pollFirst());
		}
		while (!stack2.isEmpty()) {
			stack.addFirst(stack2.pollFirst());
		}
		return reverseStack(stack);
	}

	private static Deque<Integer> reverseStack(Deque<Integer> stack) {
		Deque<Integer> ans = new LinkedList<>();
		while (!stack.isEmpty()) {
			ans.push(stack.pollFirst());
		}
		return ans;
	}
}
