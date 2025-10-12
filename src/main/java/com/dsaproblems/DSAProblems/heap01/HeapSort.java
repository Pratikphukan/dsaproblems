package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.List;

public class HeapSort {
	public static void main(String[] args) {
		List<Integer> a = List.of(8, 2, 6, 1, 5, 7, 4);
		ArrayList<Integer> A = new ArrayList<Integer>(a);
		// System.out.println(heapSort1(A));
		System.out.println(heapSort2(A));
	}

	private static ArrayList<Integer> heapSort2(ArrayList<Integer> A) {
		maxHeapify(A);
		System.out.println(A);
		int len = A.size();
		for (int i = 0; i < len; i++) {
			int item = A.get(0);
			A.set(0, A.get(len - i - 1));
			A.set(len - i - 1, item);
			int idx = 0; // index that is not in its correct position
			while (idx < len - i - 1) {
				int minidx = idx;
				int l = 2 * idx + 1, r = 2 * idx + 2;
				if (l < len - i - 1 && A.get(minidx) < A.get(l)) { // take care that it is not arr[idx]>arr[l]
					minidx = l;
				}
				if (r < len - i - 1 && A.get(minidx) < A.get(r)) {
					minidx = r;
				}
				if (minidx != idx) {
					int t = A.get(minidx);
					A.set(minidx, A.get(idx));
					A.set(idx, t);
				} else {
					break;
				}
				idx = minidx; // idx can be left or right
			}
		}
		return A;
	}

	private static void maxHeapify(ArrayList<Integer> A) {
		for (int i = (A.size() / 2 - 1); i >= 0; i--) { // percolate down approach
			int idx = i;
			while (idx < A.size() / 2) {
				int minidx = idx;
				int l = 2 * idx + 1, r = 2 * idx + 2;
				if (l < A.size() && A.get(minidx) < A.get(l)) { // take care that it is not arr[idx]>arr[l]
					minidx = l;
				}
				if (r < A.size() && A.get(minidx) < A.get(r)) {
					minidx = r;
				}
				if (minidx != idx) {
					int t = A.get(minidx);
					A.set(minidx, A.get(idx));
					A.set(idx, t);
				} else {
					break; // this is necessary if minidx==idx
				}
				idx = minidx; // idx can be left or right
			}
		}
	}

	private static ArrayList<Integer> heapSort1(ArrayList<Integer> A) { // DESCENDING ORDER
		minHeapify(A);
		System.out.println(A);
		int len = A.size();
		for (int i = 0; i < len; i++) {
			int item = A.get(0);
			A.set(0, A.get(len - i - 1));
			A.set(len - i - 1, item);
			int idx = 0; // index that is not in its correct position
			while (idx < len - i - 1) {
				int minidx = idx;
				int l = 2 * idx + 1, r = 2 * idx + 2;
				if (l < len - i - 1 && A.get(minidx) > A.get(l)) { // take care that it is not arr[idx]>arr[l]
					minidx = l;
				}
				if (r < len - i - 1 && A.get(minidx) > A.get(r)) {
					minidx = r;
				}
				if (minidx != idx) {
					int t = A.get(minidx);
					A.set(minidx, A.get(idx));
					A.set(idx, t);
				} else {
					break;
				}
				idx = minidx; // idx can be left or right
			}
		}
		return A;
	}

	private static void minHeapify(ArrayList<Integer> A) {
		for (int i = (A.size() / 2 - 1); i >= 0; i--) { // percolate down approach
			int idx = i;
			while (idx < A.size() / 2) {
				int minidx = idx;
				int l = 2 * idx + 1, r = 2 * idx + 2;
				if (l < A.size() && A.get(minidx) > A.get(l)) { // take care that it is not arr[idx]>arr[l]
					minidx = l;
				}
				if (r < A.size() && A.get(minidx) > A.get(r)) {
					minidx = r;
				}
				if (minidx != idx) {
					int t = A.get(minidx);
					A.set(minidx, A.get(idx));
					A.set(idx, t);
					idx = minidx; // idx can be left or right
				}
			}
		}
	}

}
