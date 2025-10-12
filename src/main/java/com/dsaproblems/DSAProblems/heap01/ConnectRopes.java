package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConnectRopes {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(16, 7, 3, 5, 9, 8, 6, 15));

        System.out.println(costOfConnectingv1(A));
        System.out.println(costOfConnectingv3(A));
        System.out.println(costOfConnectingv4(A));
    }

    //working code, no change in performance
    //Building a heap from an unsorted list using addAll operation is (O(n))
    // because the heapify process (used internally) organizes all elements in linear time
    private static int costOfConnectingv1(List<Integer> input) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(input);
        if (minHeap.size() == 1) {
            return 0;
        }
        int minimumCostOfConnecting = 0;
        while (minHeap.size() > 1) {
            int firstMin = minHeap.poll();
            int secondMin = minHeap.poll();
            int sumOfMins = firstMin + secondMin;
            minHeap.add(sumOfMins);
            minimumCostOfConnecting += sumOfMins;
        }
        return minimumCostOfConnecting;
    }

    // working code, no change in performance
    //Each iteration: remove two elements (O(log(n)) each), insert one (O(log(n))), repeated (n-1) times.
    private static int costOfConnectingv3(List<Integer> input) {
        Queue<Integer> min_heap = new PriorityQueue<>();
        for (int num : input) {
            min_heap.offer(num);
        }
        int minCost = 0;
        if (min_heap.size() == 1) { //the single-element check can be removed; the loop handles it
            return min_heap.poll();
        }
        while (min_heap.size() > 1) { //iterate until only one element remains in the min heap
            int firstMin = min_heap.poll();
            int secondMin = min_heap.poll();
            int sumOfMins = firstMin + secondMin;
            min_heap.offer(sumOfMins);
            minCost += sumOfMins;
        }
        return minCost;
    }

    // working code but showing TLE
    private static int costOfConnectingv4(List<Integer> input) {
        Collections.sort(input);
        int sum = 0;
        int firstMin;
        int secondMin;
        int len;
        int temp;
        while (input.size() > 1) {
            firstMin = input.remove(0);
            secondMin = input.remove(0);
            input.add(firstMin + secondMin);
            sum += (firstMin + secondMin);
            len = input.size();
            for (int j = len - 2; j >= 0; j--) {
                if (input.get(j) > input.get(j + 1)) {
                    temp = input.get(j);
                    input.set(j, input.get(j + 1));
                    input.set(j + 1, temp);
                }
            }
        }
        return sum;
    }

}
