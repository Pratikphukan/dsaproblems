package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class MinAbsoluteDifference {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(List.of(5, 17, 100, 11));
        System.out.println(findMinimumAbsoluteDifferencev1(A));
        System.out.println(findMinimumAbsoluteDifferencev2(A));
    }

    //working solution
    /*
    Time Complexity: Building the heap: O(n log n), Removing elements from the heap: O(n log n), Overall: O(n log n)
     */
    private static int findMinimumAbsoluteDifferencev2(ArrayList<Integer> A) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num : A)
            minHeap.offer(num);
        int min = Integer.MAX_VALUE;
        int first = minHeap.poll(); // Get the first element
        while (!minHeap.isEmpty()) {
            min = Math.min(min, minHeap.peek() - first);
            first = minHeap.poll();
        }
        return min;
    }

    //working code
    private static int findMinimumAbsoluteDifferencev1(ArrayList<Integer> A) {
        Collections.sort(A);
        int min = Math.abs(A.get(1) - A.get(0));
        for (int i = 1; i < A.size() - 1; i++) {
            if (min > Math.abs(A.get(i + 1) - A.get(i))) {
                min = Math.abs(A.get(i + 1) - A.get(i));
            }
        }
        return min;
    }
}
