package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMinOfWindowOfSizeK {

	public static void main(String[] args) {
		// 2, 5, 4, 6, 8|3
		// 1, 2, 3, 1, 2|1
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2));
		int window = 1;
		System.out.println(findMinOfWindowOfSizeKAndGetLargest(input, window));
		System.out.println(findMinOfWindowOfSizeKAndGetLargestv1(input, window));
	}

	private static int findMinOfWindowOfSizeKAndGetLargestv1(List<Integer> input, int window) {
		Queue<Integer> min_heap = new PriorityQueue<>();
		List<Integer> mins = new ArrayList<>();
		for (int i = 0; i < window; i++) {
			min_heap.offer(input.get(i));
		}
		mins.add(min_heap.peek());
		for (int i = 1; i <= input.size() - window; i++) {
			min_heap.remove(input.get(i - 1));
			min_heap.offer(input.get(i + window - 1));
			mins.add(min_heap.peek());
		}
		int ans = Integer.MIN_VALUE;
		for (Integer num : mins) {
			ans = Math.max(ans, num);
		}
		return ans;
	}

	private static int findMinOfWindowOfSizeKAndGetLargest(List<Integer> input, int window) {
		Queue<Integer> min_heap = new PriorityQueue<>();
		List<Integer> mins = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			min_heap.offer(input.get(i));
			if (i - window + 1 >= 0) {
				mins.add(min_heap.peek());
				min_heap.remove(input.get(i - window + 1));

			}
		}
		int ans = Integer.MIN_VALUE;
		for (Integer num : mins) {
			ans = Math.max(ans, num);
		}
		return ans;
	}

}
