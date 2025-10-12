package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MergeSortedLists {

	public static void main(String[] args) {
		Stack<Integer> stack1 = new Stack<>();
		stack1.push(3);
		stack1.push(5);
		stack1.push(10);
		stack1.push(15);
		System.out.println(stack1);
		Stack<Integer> stack2 = new Stack<>();
		stack2.push(2);
		stack2.push(4);
		stack2.push(6);
		stack2.push(7);
		stack2.push(9);
		System.out.println(stack2);

		Stack<Integer> a = new Stack<>();
		a.push(2);
		a.push(7);
		System.out.println(a);

		Deque<Integer> b = new LinkedList<>();
		b.push(2);
		b.push(7);
		System.out.println(b);

		System.out.println(mergeSortedStacks2(stack1, stack2));
	}

	private static Deque<Integer> mergeSortedStacks3(Deque<Integer> stack1, Deque<Integer> stack2) {
		Deque<Integer> ans = new LinkedList<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			if (stack1.peekFirst() > stack2.peekFirst()) {
				ans.addFirst(stack1.pop());
			} else {
				ans.addFirst(stack2.pop());
			}
		}
		while (!stack1.isEmpty()) {
			ans.addFirst(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			ans.addFirst(stack2.pop());
		}
		return ans;
	}

	private static Deque<Integer> mergeSortedStacks2(Stack<Integer> stack1, Stack<Integer> stack2) {
		Deque<Integer> ans = new ArrayDeque<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			if (stack1.peek() > stack2.peek()) {
				ans.addFirst(stack1.pop());
			} else {
				ans.addFirst(stack2.pop());
			}
		}
		while (!stack1.isEmpty()) {
			ans.addFirst(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			ans.addFirst(stack2.pop());
		}
		return ans;
	}

	private static Stack<Integer> mergeSortedStacks(Stack<Integer> stack1, Stack<Integer> stack2) {
		Stack<Integer> ans = new Stack<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			int a = stack1.peek(), b = stack2.peek();
			if (a > b) {
				ans.push(stack1.pop());
			} else {
				ans.push(stack2.pop());
			}
		}
		while (!stack1.isEmpty()) {
			ans.push(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			ans.push(stack2.pop());
		}
		System.out.println(ans);
		ans = reverseStack(ans);
		return ans;
	}

	private static Stack<Integer> reverseStack(Stack<Integer> stack) {
		Stack<Integer> ans = new Stack<>();
		while (!stack.isEmpty()) {
			ans.push(stack.pop());
		}
		System.out.println(ans);
		return ans;
	}

	// this won't work because the stacks are sorted from below(smallest) to
	// top(largest)
	private static Stack<Integer> mergeSortedStacks1(Stack<Integer> stack1, Stack<Integer> stack2) {
		Stack<Integer> ans = new Stack<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			int a = stack1.peek(), b = stack2.peek();
			if (a > b) {
				ans.push(stack1.pop());
			} else {
				ans.push(stack2.pop());
			}
		}
		while (!stack1.isEmpty()) {
			ans.push(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			ans.push(stack2.pop());
		}
		return ans;
	}

}
