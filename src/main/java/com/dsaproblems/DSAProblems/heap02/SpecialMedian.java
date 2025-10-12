package com.dsaproblems.DSAProblems.heap02;

import java.util.*;

public class SpecialMedian {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(4, 6, 8, 4));
        System.out.println(findSpecialMedianv1(A));
    }

    private static int findSpecialMedianv1(ArrayList<Integer> input) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = input.size() - 1; i >= 0; i--) {
            temp.add(input.get(i));
        }
        if (checkMedian(input) || checkMedian(temp)) {
            return 1;
        }
        return 0;
    }

    private static boolean checkMedian(ArrayList<Integer> A) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(A.get(0));

        for (int i = 1; i < A.size() - 1; i++) {

            if (A.get(i) <= maxHeap.peek()) {
                maxHeap.add(A.get(i));
            } else {
                minHeap.add(A.get(i));
            }
            //either both the heaps will have equal number of elements or max-heap will have one more element
            // than the min heap
            if (maxHeap.size() > minHeap.size() + 1) {

                minHeap.add(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            if (maxHeap.size() == minHeap.size()) {
                //we have even number of elements , take average of middle elements
                double ans = ((long) maxHeap.peek() + minHeap.peek()) / 2.0;

                if (ans == A.get(i + 1)) {
                    return true;
                }
            } else {

                if (maxHeap.peek().equals(A.get(i + 1))) {
                    return true;
                }
            }
        }
        return false;
    }
}
