package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution01 {

	static class ListLimits {

		private Integer startIdx;

		private Integer endIdx;

		public ListLimits(Integer startIdx, Integer endIdx) {
			this.startIdx = startIdx;
			this.endIdx = endIdx;
		}

		public Integer getStartIdx() {
			return startIdx;
		}

		public void setStartIdx(Integer startIdx) {
			this.startIdx = startIdx;
		}

		public Integer getEndIdx() {
			return endIdx;
		}

		public void setEndIdx(Integer endIdx) {
			this.endIdx = endIdx;
		}
	}

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(-9, -13, 6, -28, 27, -5, -27, 17, 15, -17, -25, 6, -8, 2, -13,
				-13, -23, 21, -4, 22, -9, -10, 0, -28, -11, 8, 8, 17));
		// 1, 2, -3
		// 2, 2, 1, -3, 4, 3, 1, -2, -3, 2
		// -9, -13, 6, -28, 27, -5, -27, 17, 15, -17, -25, 6, -8, 2, -13, -13, -23, 21,
		// -4, 22, -9, -10, 0, -28, -11, 8, 8, 17
		// [-8,8,-1,-16,-28,-27,15,-14,14,-27,-5,-6,-25,-11,28,29,-3,-25,17,-25,4,-20,2,1,-17,-10,-25]
		System.out.println(findLongestSubarrayWithSumv1(A));
	}

	private static ArrayList<Integer> findLongestSubarrayWithSumv1(List<Integer> A) {
		List<Integer> prefix = new ArrayList<>();
		prefix.add(A.get(0));
		for (int i = 1; i < A.size(); i++) {
			prefix.add(prefix.get(i - 1) + A.get(i));
		}
		Map<Integer, ListLimits> map = new HashMap<>();
		map.put(0, new ListLimits(-1, null));
		for (int i = 0; i < prefix.size(); i++) {
			int key = prefix.get(i);
			if (map.containsKey(key)) {
				map.get(key).setEndIdx(i);
			} else {
				map.put(key, new ListLimits(i, null));
			}
		}
		int maxLength = 0;
		int startIdx = -2;
		int endIdx = -2;
		for (Map.Entry<Integer, ListLimits> entry : map.entrySet()) {
			if (entry.getValue().getEndIdx() != null) {
				if (maxLength < (entry.getValue().getEndIdx() - entry.getValue().getStartIdx())) {
					maxLength = entry.getValue().getEndIdx() - entry.getValue().getStartIdx();
					startIdx = entry.getValue().getStartIdx();
					endIdx = entry.getValue().getEndIdx();
				}
				if (maxLength == (entry.getValue().getEndIdx() - entry.getValue().getStartIdx())) {
					if (startIdx > entry.getValue().getStartIdx()) {
						maxLength = entry.getValue().getEndIdx() - entry.getValue().getStartIdx();
						startIdx = entry.getValue().getStartIdx();
						endIdx = entry.getValue().getEndIdx();
					}
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = startIdx + 1; i <= endIdx; i++) {
			ans.add(A.get(i));
		}
		return ans;
	}

}
