package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterestingArray {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(9, 17));
		System.out.println(reduceArrayToOneElementOfZero(input));
		System.out.println(reduceArrayToOneElementOfZerov1(input));
	}

	private static boolean reduceArrayToOneElementOfZerov1(List<Integer> input) {
		int splits = 0;
		int merges = 0;
		int zeroCount = 0;
		int oneCount = 0;
		for (Integer element : input) {
			if (element % 2 == 1) {
				splits += 2;
				oneCount += 1;
			} else {
				splits += 1;
			}
			merges += 1;
			zeroCount += 1;
		}
		if (oneCount % 2 == 0) {
			merges += oneCount / 2;
			zeroCount += oneCount / 2;
		} else {
			merges += (oneCount - 1) / 2;
			zeroCount += (oneCount - 1) / 2;
			oneCount += 1;
		}
		if (oneCount % 2 == 0) {
			return true;
		}
		return false;
	}

	private static boolean reduceArrayToOneElementOfZero(List<Integer> input) {
		int oddCount = 0;
		for (Integer element : input) {
			if (element % 2 == 1) {
				oddCount += 1;
			}
		}
		if (oddCount % 2 == 0) {
			return true;
		}
		return false;
	}

}
