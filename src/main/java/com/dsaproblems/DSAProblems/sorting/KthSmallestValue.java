package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestValue {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(10, 7, 11, 30, 20, 38, 2, 45));
		int B = 3;
		System.out.println(findKthSmallestv1(A, B));
		System.out.println(findKthSmallestv2(A, B));
		System.out.println(findKthSmallestv3(A, B));
	}

	private static Integer findKthSmallestv3(List<Integer> input, int B) {
		int minForIdx = Integer.MAX_VALUE;
		int idxToReplace = -1;
		for (int i = 0; i < B; i++) {
			minForIdx = input.get(i);
			for (int j = i + 1; j < input.size(); j++) {
				if (input.get(j) < minForIdx) {
					minForIdx = input.get(j);
					idxToReplace = j;
				}
			}
			if (idxToReplace > -1) {
				int temp = input.get(i);
				input.set(i, minForIdx);
				input.set(idxToReplace, temp);
			}
			idxToReplace = -1;
		}
		return input.get(B - 1);
	}

	private static Integer findKthSmallestv2(List<Integer> input, int B) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < B; i++) {
			maxHeap.add(input.get(i));
		}
		for (int i = B; i < input.size(); i++) {
			if (input.get(i) < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.add(input.get(i));
			}
		}
		return maxHeap.peek();
	}

	private static Integer findKthSmallestv1(List<Integer> input, int B) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (Integer num : input) {
			minHeap.add(num);
		}
		for (int i = 0; i < B - 1; i++) {
			minHeap.poll();
		}
		return minHeap.peek();
	}

}
