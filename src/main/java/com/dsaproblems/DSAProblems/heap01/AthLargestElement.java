package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AthLargestElement {

    public static void main(String[] args) {
        List<Integer> B = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int A = 4;
        System.out.println(findAthLargestElementsv1(A, B));
    }

    //It maintains a min-heap of size (A), so each insert and poll operation is (O(logA)).
    //For (n) elements, the total time complexity is (O(n*logA)), which is efficient for this use case.
    //No unnecessary operations are performed, and space usage is minimal.
    //SC: O(A) for the heap
    private static ArrayList<Integer> findAthLargestElementsv1(int A, List<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < input.size(); i++) {
            minHeap.offer(input.get(i));
            if (minHeap.size() > A) {
                minHeap.poll();
            }
            if (i < (A - 1)) {
                ans.add(-1);
            } else {
                ans.add(minHeap.peek());
            }
        }
        return ans;
    }
}
