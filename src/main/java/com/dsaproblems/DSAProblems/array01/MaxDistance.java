package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxDistance {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 5, 4, 2));
		System.out.println(findMaximumValueOfJandIv1(input));
		System.out.println(findMaximumValueOfJandIv2(input));
	}

	private static int findMaximumValueOfJandIv2(List<Integer> input) {
		int len = input.size();
		int rmax[] = new int[len];
		int i, j, max = -1;
		// rmax stores the maximum element in the suffix
		for (i = len - 1; i >= 0; i--) {
			rmax[i] = (i == len - 1 || input.get(i) > rmax[i + 1]) ? input.get(i) : rmax[i + 1];
		}
		for (i = 0, j = 0; i < len && j < len;) {
			if (input.get(i) <= rmax[j]) {
				if (j - i > max)
					max = j - i;
				j++;
			} else {
				i++;
			}
		}
		return max;
	}

	private static int findMaximumValueOfJandIv1(List<Integer> input) {
		int len = input.size();
		if (len == 1) {
			return 0;
		}
		List<Element> elements = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			elements.add(new Element(input.get(i), i));
		}
		Collections.sort(elements);
		System.out.println(elements);
		int ans = 0;
		int maxIdx = elements.get(len - 1).getIndex();
		for (int i = len - 2; i >= 0; i--) {
			ans = Math.max(ans, maxIdx - elements.get(i).getIndex()); // maximize j-i, find max to the right
			maxIdx = Math.max(maxIdx, elements.get(i).getIndex());
		}
		return ans;
	}

}
