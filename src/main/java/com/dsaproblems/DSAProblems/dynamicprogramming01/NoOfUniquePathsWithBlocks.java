package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;

public class NoOfUniquePathsWithBlocks {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
		System.out.println(A);
		System.out.println(noOfUniquePaths1(A, A.size() - 1, A.get(0).size() - 1));
		System.out.println(A);
		// System.out.println(noOfUniquePaths(A, A.size() - 1, A.get(0).size() - 1));
	}

	private static Integer noOfUniquePaths1(ArrayList<ArrayList<Integer>> A, int i, int j) {
		if (A.get(i).get(j) == 0) {
			if (i == 0 || j == 0) {
				A.get(i).set(j, 1);
			} else {
				int item = noOfUniquePaths1(A, i - 1, j) + noOfUniquePaths1(A, i, j - 1);
				A.get(i).set(j, item);
			}
		}
		return A.get(i).get(j);
	}

	// no of paths from (0,0) to (s,e)
	private static int noOfUniquePaths(ArrayList<ArrayList<Integer>> A, int s, int e) {
//		if (s < 0 || e < 0) {
//			return 0;
//		}
		if (s == 0 || e == 0) {
			return 1;
		}
		return noOfUniquePaths(A, s - 1, e) + noOfUniquePaths(A, s, e - 1);
	}

}
