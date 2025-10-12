package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program1 {

	public static void main(String[] args) {

		List<Integer> arr0 = List.of(-1, 4, 7, 6, -2, 7, 8, 10);
		List<Integer> arr1 = new ArrayList<>(arr0);
		int s = 2, e = 4;
		// System.out.println(reverseSubArray1(arr1, s, e));
		System.out.println(reverseSubArray2(arr1, s, e));

		List<Integer> arr2 = new ArrayList<>();
		arr2.add(-1);
		arr2.add(6);
		arr2.add(3);
		arr2.add(2);
		arr2.add(8);
		arr2.add(9);
		arr2.add(10);
		s = 1;
		e = 2;
		// System.out.println(reverseSubArray1(arr2, s, e));
		System.out.println(reverseSubArray2(arr2, s, e));

		List<Integer> arr3 = List.of(-3, 4, 2, 8, 7, 9, 6, 2, 10);
		List<Integer> arr4 = new ArrayList<>(arr3);
		s = 3;
		e = 7;
		// System.out.println(reverseSubArray1(arr4, s, e));
		System.out.println(reverseSubArray2(arr4, s, e));

	}

	private static List<Integer> reverseSubArray2(List<Integer> arr, int s, int e) {
		while (s < e) {
			Collections.swap(arr, s, e);
			s++;
			e--;
		}
		return arr;
	}

	private static List<Integer> reverseSubArray1(List<Integer> arr, int s, int e) {
		while (s < e) {
			// swap1(arr, s, e);
			swap2(arr, s, e);
			System.out.printf("%d, %d\t", s, e);
			s++;
			e--;
		}
		return arr;
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
