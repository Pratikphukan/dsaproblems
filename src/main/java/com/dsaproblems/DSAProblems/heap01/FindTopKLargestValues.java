package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindTopKLargestValues {

	public static void main(String[] args) {
		List<Integer> a = List.of(8, 3, 10, 4, 11, 2, 7, 6, 5, 1);
		ArrayList<Integer> A = new ArrayList<>(a);
		int B = 4;
		System.out.println(findKLargerElements1(A, B));
	}

	private static PriorityQueue<Integer> findKLargerElements1(ArrayList<Integer> A, int B) {
		PriorityQueue<Integer> minheap = new PriorityQueue<>();
		for (int i = 0; i < B; i++) {
			minheap.add(A.get(i));
		}
		for (int i = B; i < A.size(); i++) {
			if (minheap.peek() < A.get(i)) {
				minheap.poll();
				minheap.add(A.get(i));
			}
		}
		return minheap;
	}

}
