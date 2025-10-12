package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.List;

public class Flip {

	public static void main(String[] args) {
		String input = "0100100001";
		// String input = "110010011";
		System.out.println(flipv1(input));
		System.out.println(flipv2(input));
	}

	private static ArrayList<Integer> flipv2(String input) {
		int cur = 0;
		int maxx = 0, l = 0, r = 0;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.add(-1);
		ans.add(-1);
		// basic kadane's algorithm implementation
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '1')
				cur--;
			else
				cur++;
			if (cur > maxx) {
				ans.set(0, l + 1);
				ans.set(1, r + 1);
				maxx = cur;
			}
			if (cur < 0) {
				cur = 0;
				l = i + 1;
				r = i + 1;
			} else
				r++;
		}
		if (maxx == 0) {
			return new ArrayList<Integer>();
		}
		return ans;
	}

	private static List<Integer> flipv1(String input) {
		List<Integer> flipGain = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '1')
				flipGain.add(-1);
			else
				flipGain.add(1);
		}
		int currSum = 0;
		int k = 0;
		int maxSum = 0;
		int start = -1;
		int end = -1;
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < flipGain.size(); i++) {
			if ((currSum + flipGain.get(i)) < 0) {
				k = i + 1;
				currSum = 0;
			} else {
				currSum += flipGain.get(i);
				// System.out.println(currSum);
			}
			if (currSum > maxSum) {
				maxSum = currSum;
				start = k;
				end = i;
			}
		}
		if (start == -1) {
			return res;
		}
		res.add(start + 1);
		res.add(end + 1);
		return res;
	}

}
