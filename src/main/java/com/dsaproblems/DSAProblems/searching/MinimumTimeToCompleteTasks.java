package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumTimeToCompleteTasks {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(3, 5, 1, 7, 8, 2, 5, 3, 10, 1, 4, 7, 5, 4, 6));
		int workers = 4;
		System.out.println(findMinimumTimeToCompleteTasks(A, workers));
	}

	private static int findMinimumTimeToCompleteTasks(List<Integer> A, int workers) {
		int minTime = 0; // considering number of workers as the size of array
		int maxTime = 0; // considering one worker doing all the work
		for (int i = 0; i < A.size(); i++) {
			minTime = Math.max(minTime, A.get(i));
			maxTime += A.get(i);
		}
		int possibleTime = 0;
		int ans = maxTime;
		while (minTime <= maxTime) {
			possibleTime = minTime + (maxTime - minTime) / 2;
			if (findWorkers(A, possibleTime) <= workers) {
				ans = possibleTime;
				maxTime = possibleTime - 1;
			} else {
				minTime = possibleTime + 1;
			}
		}
		return ans;
	}

	private static int findWorkers(List<Integer> A, int possibleTime) {
		int sum = 0;
		int count = 0;
		for (Integer reqTime : A) {
			sum += reqTime;
			if (sum > possibleTime) {
				count++;
				sum = reqTime;
			}
		}
		if (sum > 0) {
			count++;
		}
		return count;
	}

}
