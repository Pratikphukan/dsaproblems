package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheShipCompany {

	public static void main(String[] args) {
		int A = 4;
		int B = 3;
		List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 1));
		System.out.println(findMinAndMaxMoney(A, B, input));
	}

	private static List<Integer> findMinAndMaxMoney(int A, int B, List<Integer> input) {
		Queue<Integer> minheap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (Integer item : input) {
			minheap.add(item);
			maxHeap.add(item);
		}
		int temp1 = 0;
		int temp2 = 0;
		int max_value = 0;
		int min_value = 0;
		while (A-- > 0) {
			temp1 = maxHeap.poll();
			max_value += temp1;
			if (temp1 - 1 > 0) {
				maxHeap.add(temp1 - 1);
			}
			temp2 = minheap.poll();
			min_value += temp2;
			if (temp2 - 1 > 0) {
				minheap.add(temp2 - 1);
			}
		}
		List<Integer> ans = new ArrayList<Integer>();
		ans.add(max_value);
		ans.add(min_value);
		return ans;
	}

}
