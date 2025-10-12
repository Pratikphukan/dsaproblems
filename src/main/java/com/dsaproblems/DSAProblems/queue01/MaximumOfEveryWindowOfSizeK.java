package com.dsaproblems.DSAProblems.queue01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.dsaproblems.DSAProblems.heap01.IntegerCompare;

public class MaximumOfEveryWindowOfSizeK {

	private Deque<Integer> stack = new LinkedList<>();
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private Integer min = Integer.MIN_VALUE;

	public void push(int x) {
		minHeap.add(x);
		stack.addFirst(x);
		min = minHeap.peek();
	}

	public void pop() {
		Integer toBeRemoved = stack.peekFirst();
		if (toBeRemoved != null) {
			minHeap.remove(stack.peekFirst());
			stack.removeFirst();
			min = minHeap.peek();
		}
	}

	public int top() {
		if (stack.isEmpty()) {
			return -1;
		}
		return stack.peekFirst();
	}

	public int getMin() {
		if (stack.isEmpty()) {
			return -1;
		}
		return min;
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(10, 8, 9, 7, 6, 5, 11, 3));
		// 10,8,9,7,6,5,11,3
		// 1, 3, -1, -3, 5, 3, 6, 7
		// 1, 2, 3, 4, 2, 7, 1, 3, 6
		// 5, 5, 5, 5, 5, 5, 5
		// 3,2,3,4,5,5,4,5,6
		int k = 3;
		System.out.println(findMaximumOfEveryWindowOfSizeK(input, k));
		System.out.println(findMaximumOfEveryWindowOfSizeKv1(input, k));
		// System.out.println(findMaximumOfEveryWindowOfSizeKv2(input, k));

		System.out.println(findMaximumOfWholeWindow(input));
	}

	private static char[] findMaximumOfEveryWindowOfSizeKv2(List<Integer> input, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Integer findMaximumOfWholeWindow(List<Integer> input) {
		Integer ans = null;
		Deque<Integer> stack = new LinkedList<>();
		for (int item : input) {
			if (stack.isEmpty() || stack.peekFirst() > item)
				stack.offerFirst(item);
			while (!stack.isEmpty() && stack.peekFirst() < item) {
				stack.pollFirst();
			}
			ans = stack.peekFirst();
		}
		return ans;
	}

	private static List<Integer> findMaximumOfEveryWindowOfSizeKv1(List<Integer> input, int k) {
		Queue<Integer> max_heap = new PriorityQueue<>(new IntegerCompare());
		List<Integer> ans = new ArrayList<>();
		int len = input.size();
		for (int i = 0; i < len; i++) {
			max_heap.add(input.get(i));
			if (i - k + 1 >= 0) {
				ans.add(max_heap.peek());
				max_heap.remove(input.get(i - k + 1));
			}
		}
		return ans;
	}

	private static List<Integer> findMaximumOfEveryWindowOfSizeK(List<Integer> input, int k) {
		List<Integer> ans = new ArrayList<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < k; i++) {
			maxHeap.add(input.get(i));
		}
		ans.add(maxHeap.peek());
		for (int i = 0; i < input.size() - k; i++) {
			maxHeap.remove(input.get(i));
			maxHeap.add(input.get(i + k));
			ans.add(maxHeap.peek());
		}
		return ans;
	}

}
