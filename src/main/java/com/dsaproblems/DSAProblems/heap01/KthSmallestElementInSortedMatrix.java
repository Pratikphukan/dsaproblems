package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElementInSortedMatrix {

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(Arrays.asList(5, 9, 11));
		input.add(Arrays.asList(9, 11, 13));
		input.add(Arrays.asList(10, 12, 15));
		input.add(Arrays.asList(13, 14, 16));
		input.add(Arrays.asList(16, 20, 21));

		int B = 12;

		System.out.println(findBthSmallestElementInMatrixv1(input, B));
		System.out.println(findBthSmallestElementInMatrixv2(input, B));
	}

	private static Integer findBthSmallestElementInMatrixv2(List<List<Integer>> input, int B) {
		int rows = input.size();
		int cols = input.get(0).size();
		Queue<Integer> maxHeap = new PriorityQueue<>(new IntegerCompare());
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (maxHeap.size() < B) {
					maxHeap.add(input.get(i).get(j));
				} else {
					if (input.get(i).get(j) < maxHeap.peek()) {
						maxHeap.poll();
						maxHeap.offer(input.get(i).get(j));
					}
				}
			}
		}
		return maxHeap.peek();
	}

	private static Integer findBthSmallestElementInMatrixv1(List<List<Integer>> input, int B) {
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (List<Integer> row : input) {
			for (Integer element : row) {
				if (maxHeap.size() < B) {
					maxHeap.add(element);
				} else {
					if (maxHeap.peek() > element) {
						maxHeap.poll();
						maxHeap.add(element);
					}
				}
			}
		}
		return maxHeap.peek();
	}

}
