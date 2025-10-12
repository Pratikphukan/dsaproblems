package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;

public class Program3 {

	public static void main(String[] args) {
		List<Integer> arr0 = List.of(-1, 4, 7, 6, -2, 7, 8, 10);
		List<Integer> arr1 = new ArrayList<>(arr0);
		int k = 17;
		// System.out.println(rotateArray(arr1, k));
		rotateArray(arr1, k);
		System.out.println(arr1);

		List<Integer> arr2 = new ArrayList<>();
		arr2.add(-1);
		arr2.add(6);
		arr2.add(3);
		arr2.add(2);
		arr2.add(8);
		arr2.add(9);
		arr2.add(10);
		k = 9;
		// System.out.println(rotateArray(arr2, k));
		rotateArray(arr2, k);
		System.out.println(arr2);

		List<Integer> arr3 = List.of(-3, 4, 2, 8, 7, 9, 6, 2, 10);
		List<Integer> arr4 = new ArrayList<>(arr3);
		k = 21;
		// System.out.println(rotateArray(arr4, k));
		rotateArray(arr4, k);
		System.out.println(arr4);

		List<Integer> arr5 = List.of(3, -2, 1, 4, 6, 9, 8);
		List<Integer> arr6 = new ArrayList<>(arr5);
		k = 523;
		// System.out.println(rotateArray(arr6, k));
		rotateArray(arr6, k);
		System.out.println(arr6);
	}

	private static void rotateArray(List<Integer> arr, int k) {
		k = k % (arr.size());
		reverseSubArray(arr, 0, arr.size() - 1);
		reverseSubArray(arr, 0, arr.size() - k - 1);
		reverseSubArray(arr, arr.size() - k, arr.size() - 1);
	}

	private static void reverseSubArray(List<Integer> arr, int s, int e) {
		while (s < e) {
			// swap1(arr, s, e);
			swap2(arr, s, e);
			// System.out.printf("%d, %d\t", s, e);
			s++;
			e--;
		}
	}

	private static void swap2(List<Integer> arr, int i, int j) {
		arr.set(i, arr.get(i) + arr.get(j));
		arr.set(j, arr.get(i) - arr.get(j));
		arr.set(i, arr.get(i) - arr.get(j));
	}

	private static void swap1(List<Integer> arr, int i, int j) {
		int temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}

}
