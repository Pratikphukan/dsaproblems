package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumAfterBNegations {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(57, 3, -14, -87, 42, 38, 31, -7, -28, -61));
        int B = 10;
        System.out.println(maxSumAfterBNegationsv1(A, B));
        System.out.println(maxSumAfterBNegationsv2(A, B));

    }

    //working code
    private static int maxSumAfterBNegationsv2(List<Integer> input, int numberOfNegations) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int x : input)
            minHeap.offer(x);
        int currMin = -100;
        while (numberOfNegations > 0) {
            currMin = minHeap.poll();
            if (currMin == 0) {
                numberOfNegations = 0;
            } else if (currMin < 0) {
                minHeap.offer(-1 * currMin);
            } else {
                if (numberOfNegations % 2 == 0) {
                    minHeap.offer(currMin);
                } else {
                    minHeap.offer(-1 * currMin);
                }
                numberOfNegations = 0;
            }
            numberOfNegations--;
        }
        int ans = 0;
        while (!minHeap.isEmpty()) {
            ans += minHeap.poll();
        }
        return ans;
    }

    //working code
    //Building the min-heap: (O(n))
    //Each negation: O(log n) (for poll and add), repeated B times
    //Summing up: O(nlog(n)) since each poll is O(log(n))
    private static int maxSumAfterBNegationsv1(List<Integer> input, int numberOfNegations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(input);
        for (int i = 0; i < numberOfNegations && !minHeap.isEmpty(); i++) {
            minHeap.add(-1 * minHeap.poll());
        }
        int sum = 0;
        while (!minHeap.isEmpty()) {
            sum += minHeap.poll();
        }
        return sum;
    }

}
