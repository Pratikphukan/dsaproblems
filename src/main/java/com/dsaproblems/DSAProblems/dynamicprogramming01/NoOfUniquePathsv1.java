package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoOfUniquePathsv1 {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		System.out.println(noOfUniquePathsv1(A, A.size() - 1, A.get(0).size() - 1));

		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
		B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1)));
		B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0)));
		System.out.println(noOfUniquePathsWithBlocksv1(B, B.size() - 1, B.get(0).size() - 1));

		System.out.println(noOfUniquePathsWithBlocksv2(B, B.size() - 1, B.get(0).size() - 1));

	}

	private static int noOfUniquePathsWithBlocksv2(ArrayList<ArrayList<Integer>> input, int desti, int destj) {
		int mod = 1000000007;
		List<Integer> dp = new ArrayList<>();
		for (int i = 0; i < input.get(0).size(); i++) {
			if (input.get(0).get(i) == 0) {
				if (i > 0 && dp.get(i - 1) == 0) {
					dp.add(0);
				} else {
					dp.add(1);
				}
			} else {
				dp.add(0);
			}
		}
		for (int i = 1; i < input.size(); i++) {
			for (int j = 0; j < dp.size(); j++) {
				if (input.get(i).get(j) == 0) {
					if (j > 0) {
						dp.set(j, (dp.get(j - 1) % mod + dp.get(j) % mod) % mod);
					}
				} else {
					dp.set(j, 0);
				}
			}
		}
		return dp.get(destj) % mod;
	}

	private static int noOfUniquePathsWithBlocksv1(ArrayList<ArrayList<Integer>> A, int desti, int destj) {
		if (desti < 0 || destj < 0) {
			return 0;
		}
		if (desti == 0 && destj == 0 && A.get(desti).get(destj) == 0) {
			return 1;
		}
		if (A.get(desti).get(destj) == 1) {
			return 0;
		}
		return noOfUniquePathsWithBlocksv1(A, desti - 1, destj) + noOfUniquePathsWithBlocksv1(A, desti, destj - 1);
	}

	// desti is 0 which means it is the first row
	// from any point in first row to any point in the first row we can go in 1 path
	// similarly for destj
	private static int noOfUniquePathsv1(ArrayList<ArrayList<Integer>> input, int desti, int destj) {
		if (desti == 0 || destj == 0) {
			return 1;
		}
		return noOfUniquePathsv1(input, desti - 1, destj) + noOfUniquePathsv1(input, desti, destj - 1);
	}

}
