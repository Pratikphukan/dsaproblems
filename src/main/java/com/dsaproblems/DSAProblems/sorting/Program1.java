package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program1 {

	public static void main(String[] args) {
		List<Integer> a = List.of(10, 3, 8, 15, 6, 12, 2, 18, 7, 15, 14);
		ArrayList<Integer> A = new ArrayList<Integer>(a);
		// 14, 6, 8, 18, 1, 19, 4, 11, 20, 10, 33, 29
		// 10, 3, 8, 15, 6, 12, 2, 18, 7, 15, 14
		// 18, 8, 6, 3, 11, 14, 23, 20, 31, 27
		System.out.println(findPositionOfFirstElementv1(A));
		System.out.println(findPositionOfFirstElementv2(A));

		// the moment left exceeds right, no need to continue the process
//		int l = 1, r = A.size()-1;
//		while(l<=r) {
//			if(A.get(l)<=A.get(0)) {
//				l++;
//			}else if(A.get(r)>A.get(0)) {
//				r--;
//			}else {
//				int tmp = A.get(l);
//				A.set(l, A.get(r));
//				A.set(r, tmp);
//				l++;r--;
//			}
//		}
//		A.set(l-1, A.get(0));
//		System.out.println(A);

//		for(int i = 1; i < A.size(); i++) {
//			if(A.get(i)<=A.get(0)) {
//				l++;
//			}else {
//				r--;
//			}
//		}
//		System.out.println(l);
//		System.out.println(r);

//		int l=0,r=A.size()-1;
//		for(int i = 1; i < A.size(); i++) {
//			if(A.get(i)<=A.get(0)) {
//				temp.add(A.get(i));
//				l++;
//			}else {
//				temp.add(temp.size()-1, A.get(i));
//				r--;
//			}
//		}
//		System.out.println(temp);
		Collections.sort(A);
		System.out.println(A);
		List<Integer> c = List.of(10, 3, 8, 15, 6, 12, 2, 18, 7, 15, 14);
		ArrayList<Integer> C = new ArrayList<Integer>(c);
		quickSort(C, 0, C.size() - 1);
		System.out.println(C);
	}

	private static List<Integer> findPositionOfFirstElementv2(ArrayList<Integer> input) {
		int low = 0;
		int high = input.size() - 1;
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			temp.add(0);
		}
		for (int i = 1; i < input.size(); i++) {
			if (input.get(i) <= input.get(0)) {
				temp.set(low, input.get(i));
				low++;
			} else {
				temp.set(high, input.get(i));
				high--;
			}
		}
		temp.set(high, input.get(0));
		return temp;
	}

	private static ArrayList<Integer> findPositionOfFirstElementv1(ArrayList<Integer> input) {
		int low = 1;
		int high = input.size() - 1;
		Integer temp = null;
		while (low < high) { // low==high, you will substitute the same element
			if (input.get(low) <= input.get(0)) { // multiple conditions in the "else if" sequence are true, only the
													// code block associated with the first true condition is executed
				low++;
			} else if (input.get(high) > input.get(0)) {
				high--;
			} else {
				temp = input.get(low);
				input.set(low, input.get(high));
				input.set(high, temp);
				low++;
				high--;
			}
		}
		temp = input.get(0);
		input.set(0, input.get(high));
		input.set(high, temp);
		return input;
	}

	private static void quickSort(ArrayList<Integer> C, int start, int end) {
		if (start >= end) {
			return;
		}
		int p = rearrange(C, start, end);
		quickSort(C, start, p - 1);
		quickSort(C, p + 1, end);
	}

	private static int rearrange(ArrayList<Integer> C, int start, int end) {
		int low = start + 1;
		int high = end;
		Integer temp = null;
		while (low <= high) {
			if (C.get(low) <= C.get(start)) {
				low++;
			} else if (C.get(high) > C.get(start)) {
				high--;
			} else {
				temp = C.get(low);
				C.set(low, C.get(high));
				C.set(high, temp);
				low++;
				high--;
			}
		}
		temp = C.get(start);
		C.set(start, C.get(high));
		C.set(high, temp);
		return high;
	}

}
