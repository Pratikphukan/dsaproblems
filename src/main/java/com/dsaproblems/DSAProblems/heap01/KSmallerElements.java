package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallerElements {

	public static void main(String[] args) {
		List<Integer> a = List.of(8, 3, 10, 4, 11, 2, 7, 6, 5, 1);
		ArrayList<Integer> A = new ArrayList<Integer>(a);
		int B = 4;
		// System.out.println(findKSmallerElements(A, B));
		System.out.println(findKSmallerElements1(A, B));
	}

	private static PriorityQueue<Integer> findKSmallerElements1(ArrayList<Integer> A, int B) {
		PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
		// in a list of size of B, we need to replace the maximum element
		for (int i = 0; i < B; i++) {
			maxheap.add(A.get(i));
		}
		for (int i = B; i < A.size(); i++) {
			if (maxheap.peek() > A.get(i)) {
				maxheap.poll();
				// extract the max and insert the minimum element
				maxheap.add(A.get(i));
			}
		}
		return maxheap;
	}

	// TC to heapify is O(N) and remove k elements O(Klog(N))
	private static ArrayList<Integer> findKSmallerElements(ArrayList<Integer> A, int B) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		PriorityQueue<Integer> minheap = new PriorityQueue<>();
		for (int i = 0; i < A.size(); i++) {
			minheap.add(A.get(i));
		}
		for (int i = 0; i < B; i++) {
			int item = minheap.poll();
			ans.add(item);
		}
		return ans;
	}

}
