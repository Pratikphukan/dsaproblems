package com.dsaproblems.DSAProblems.hashing01;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarrayWithGivenSum {

	public static void main(String[] args) {
		// 5, 10, 20, 100, 105|110
		// 1, 2, 3, 4, 5|5
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		int targetSum = 5;
		System.out.println(findFirstSubarrayWithSumK(input, targetSum));
		System.out.println(findFirstSubarrayWithSumKv1(input, targetSum));
	}

	// working solution, faster approach
	private static ArrayList<Integer> findFirstSubarrayWithSumKv1(List<Integer> input, int targetSum) {
		int len = input.size();
		int left = 0;
		int right = 0;
		long sum = input.get(left);
		ArrayList<Integer> ans = new ArrayList<>();
		while (right < len) {
			if (sum == targetSum) {
				// current window sum = B
				for (int i = left; i <= right; i++)
					ans.add(input.get(i));
				return ans;
			} else if (sum < targetSum) {
				// current window sum < B
				right++;
				if (right < len)
					sum += input.get(right);
			} else {
				// current window sum > B
				sum -= input.get(left);
				left++;
				if (left > right && left < len - 1) {
					right++;
					sum += input.get(right);
				}
			}
		}
		return new ArrayList<>(Arrays.asList(-1));
	}

	// sum(i->j)=pf(j)-pf(i-1)=>pf(j)-sum(i->j)= pf(i-1)
	// working solution
	private static List<Integer> findFirstSubarrayWithSumK(List<Integer> input, int targetSum) {
		List<Long> prefix = new ArrayList<>();
		long sum = 0;
		for (int item : input) {
			sum += item;
			prefix.add(sum);
		}
		int start = -1;
		int end = -1;
		Map<Long, Integer> map = new HashMap<>();
		long currSumFromStart;
		for (int i = 0; i < prefix.size(); i++) {
			currSumFromStart = prefix.get(i);
			if (currSumFromStart == targetSum) {
				start = 0;
				end = i;
				break;
			}
			map.put(currSumFromStart, i);
			if (map.containsKey(currSumFromStart - targetSum)) {
				start = map.get(currSumFromStart - targetSum) + 1;
				end = i;
				break;
			}
		}
		List<Integer> ans = new ArrayList<>();
		if (start == -1 || end == -1) {
			ans.add(-1);
			return ans;
		}
		for (int i = start; i <= end; i++) {
			ans.add(input.get(i));
		}
		return ans;
	}

}
