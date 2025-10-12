package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MishaAndCandies {

	public static void main(String[] args) {
		// 3, 2, 3->4
		// 355,667->867
		// 324,458,481,167,939,444,388,612,943,890,953,403,653,136,168,163,186,471->231
		// 9,818,174,237,892,109,522,27,59,336,605,865,714,86,708,535,138,948,836,287,179,754,466,856,153,53,958,951,262,341,769,491,772,509,336,120,98,805,169,984,520,447,256,266,348,351,60,563,45,638,29,479,400->852
		List<Integer> A = new ArrayList<>(Arrays.asList(9, 818, 174, 237, 892, 109, 522, 27, 59, 336, 605, 865, 714, 86,
				708, 535, 138, 948, 836, 287, 179, 754, 466, 856, 153, 53, 958, 951, 262, 341, 769, 491, 772, 509, 336,
				120, 98, 805, 169, 984, 520, 447, 256, 266, 348, 351, 60, 563, 45, 638, 29, 479, 400));
		int B = 852;
		System.out.println(noOfCandiesConsumedv1(A, B));
		System.out.println(noOfCandiesConsumedv2(A, B));
		Collections.sort(A);
		System.out.println(A);
	}

	// working code
	private static int noOfCandiesConsumedv2(List<Integer> boxes, int maxCandiesLikedByMisha) {
		Queue<Integer> minHeap = new PriorityQueue<>();
		for (int x : boxes)
			minHeap.offer(x);
		int currBox = 0;
		int nextBox = 0;
		int candiesConsumed = 0;
		while (!minHeap.isEmpty()) {
			currBox = minHeap.poll();
			if (currBox > maxCandiesLikedByMisha) {
				break;
			}
			candiesConsumed += currBox / 2;
			if (minHeap.isEmpty()) {
				break;
			}
			nextBox = minHeap.poll() + (currBox - currBox / 2);
			if (nextBox <= maxCandiesLikedByMisha)
				minHeap.offer(nextBox);
		}
		return candiesConsumed;
	}

	// wprking code
	private static int noOfCandiesConsumedv1(List<Integer> boxes, int maxCandiesLikedByMisha) {
		Queue<Integer> minHeap = new PriorityQueue<>();
		for (int x : boxes)
			minHeap.offer(x);
		int currMin = 0;
		int nextMin = 0;
		int candiesConsumed = 0;
		while (minHeap.peek() <= maxCandiesLikedByMisha && minHeap.size() > 1) {
			currMin = minHeap.poll();
			nextMin = minHeap.poll();
			candiesConsumed += currMin / 2;
			minHeap.offer(nextMin + (currMin - currMin / 2));
		}
		if (minHeap.size() == 1 && minHeap.peek() <= maxCandiesLikedByMisha)
			candiesConsumed += minHeap.poll() / 2;
		return candiesConsumed;
	}

}
