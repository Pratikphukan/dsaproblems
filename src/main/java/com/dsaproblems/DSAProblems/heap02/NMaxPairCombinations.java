package com.dsaproblems.DSAProblems.heap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.dsaproblems.DSAProblems.heap01.IntegerCompare;

public class NMaxPairCombinations {

	public static void main(String[] args) {
		// 1, 4, 2, 3|2, 5, 1, 6
		// 3,4|3,4
		List<Integer> A = new ArrayList<>(Arrays.asList(1, 4, 2, 3));
		List<Integer> B = new ArrayList<>(Arrays.asList(2, 5, 1, 6));
		System.out.println(findNMaxPairSumv1(A, B));
		System.out.println(findNMaxPairSumv2(A, B));
		System.out.println(findNMaxPairSumv3(A, B));
		Integer a = 6;
		System.out.println(a.getClass().getName());
		Integer b = 9;
		Object bb = b;
		System.out.println(bb.getClass().getName());
	}

	private static ArrayList<Integer> findNMaxPairSumv3(List<Integer> A, List<Integer> B) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Collections.sort(A);
		Collections.sort(B);
		Queue<PairSum> max_heap = new PriorityQueue<>(new SumCompare());
		Set<PairSum> pairs = new HashSet<>();
		int len = A.size();
		int lastIdx1 = A.size() - 1;
		int lastIdx2 = B.size() - 1;
		PairSum currMaxSum = new PairSum(lastIdx1, lastIdx2, A.get(lastIdx1) + B.get(lastIdx2));
		max_heap.offer(currMaxSum);
		pairs.add(currMaxSum);
		PairSum pairSum1 = null;
		PairSum pairSum2 = null;
		for (int i = 0; i < len; i++) {
			currMaxSum = max_heap.poll();
			ans.add(currMaxSum.getSum());
			lastIdx1 = currMaxSum.getIdx1() - 1;
			lastIdx2 = currMaxSum.getIdx2();
			if (lastIdx1 >= 0 && lastIdx2 >= 0) {
				pairSum1 = new PairSum(lastIdx1, lastIdx2, A.get(lastIdx1) + B.get(lastIdx2));
				if (!pairs.contains(pairSum1)) {
					max_heap.offer(pairSum1);
					pairs.add(pairSum1);
				}

			}
			lastIdx1 = currMaxSum.getIdx1();
			lastIdx2 = currMaxSum.getIdx2() - 1;
			if (lastIdx1 >= 0 && lastIdx2 >= 0) {
				pairSum2 = new PairSum(lastIdx1, lastIdx2, A.get(lastIdx1) + B.get(lastIdx2));
				if (!pairs.contains(pairSum2)) {
					max_heap.offer(pairSum2);
					pairs.add(pairSum2);
				}
			}
		}
		return ans;
	}

	// working code but throws TLE
	private static ArrayList<Integer> findNMaxPairSumv2(List<Integer> A, List<Integer> B) {
		int len = A.size();
		Queue<Integer> min_heap = new PriorityQueue<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (min_heap.size() < len) {
					min_heap.offer(A.get(i) + B.get(j));
				} else {
					if (min_heap.peek() < (A.get(i) + B.get(j))) {
						min_heap.poll();
						min_heap.offer(A.get(i) + B.get(j));
					}
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			ans.add(0, min_heap.poll());
		}
		return ans;
	}

	// working code but throws runtime error
	private static ArrayList<Integer> findNMaxPairSumv1(List<Integer> A, List<Integer> B) {
		Queue<Integer> max_heap = new PriorityQueue<Integer>(new IntegerCompare());
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				max_heap.offer(A.get(i) + B.get(j));
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < A.size(); i++) {
			ans.add(max_heap.poll());
		}
		return ans;
	}

}
