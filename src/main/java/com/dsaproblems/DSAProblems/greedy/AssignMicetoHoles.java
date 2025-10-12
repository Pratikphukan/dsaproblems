package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AssignMicetoHoles {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(-4, 2, 3));
		List<Integer> B = new ArrayList<>(Arrays.asList(0, -2, 4));
		System.out.println(findMinTimeAllMiceGetIntoHolesv1(A, B));
		System.out.println(findMinTimeAllMiceGetIntoHolesv2(A, B));
	}

	private static int findMinTimeAllMiceGetIntoHolesv2(List<Integer> A, List<Integer> B) {
		if (A == null || B == null)
			return 0;

		Collections.sort(A);
		Collections.sort(B);

		int max = 0;
		int n = A.size();

		for (int i = 0; i < n; i++) {
			max = Math.max(max, Math.abs(A.get(i) - B.get(i)));
		}

		return max;
	}

	// working code
	private static int findMinTimeAllMiceGetIntoHolesv1(List<Integer> mice, List<Integer> holes) {
		Collections.sort(mice);
		Collections.sort(holes);
		int len = mice.size();
		int timeTaken;
		int totalTime = 0;
		for (int i = 0; i < len; i++) {
			timeTaken = Math.abs(mice.get(i) - holes.get(i));
			totalTime = Math.max(totalTime, timeTaken);
		}
		return totalTime;
	}

}
