package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class MagicianAndChocolates {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(2147483647, 2147483647, 2147483647, 8, 10));
        int time = 5;
        System.out.println(maximumChocolatesConsumedv1(A, time));
        System.out.println(maximumChocolatesConsumedv2(A, time));
    }

    //Space Complexity: O(n)
    //Time Complexity: O(time*log(n))
    //Each operation (poll and offer in heap) is O(log(n))
    //working code
    private static int maximumChocolatesConsumedv2(List<Integer> input, int time) {
        int MOD = 1000000007;
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (Integer num : input) {
            maxHeap.offer(num);
        }
        int max_chocolates_consumed = 0;
        int k = time;
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int max_element = maxHeap.poll();
            max_chocolates_consumed = (max_chocolates_consumed % MOD + max_element % MOD) % MOD;
            maxHeap.offer(max_element / 2);
        }
        return max_chocolates_consumed;
    }

    //working code
    private static int maximumChocolatesConsumedv1(List<Integer> input, int time) {
        int MOD = 1000000007;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(input);
        int maxChocolatesConsumed = 0;
        for (int i = 0; i < time && !maxHeap.isEmpty(); i++) {
            int maxElement = maxHeap.poll();
            maxChocolatesConsumed = (maxChocolatesConsumed % MOD + maxElement % MOD) % MOD;
            maxHeap.add(maxElement / 2);
        }
        return maxChocolatesConsumed;
    }

}
