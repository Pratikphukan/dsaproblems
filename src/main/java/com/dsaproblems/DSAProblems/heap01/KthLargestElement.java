package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {
        List<Integer> a = List.of(10, 7, 11, 5, 27, 8, 20, 45);
        ArrayList<Integer> A = new ArrayList<Integer>(a);
        if (findKthLargestv1(A, 3) == null) {
            System.out.println("Invalid case");
        } else {
            System.out.println(findKthLargestv1(A, 3));
        }
        if (findKthLargestv2(A, 3) == null) {
            System.out.println("Invalid case");
        } else {
            System.out.println(findKthLargestv2(A, 3));
        }
    }

    // using min heap
    //SC: O(B)
    //TC: O(N*log(B))-> build/maintain a heap of size B while scanning N elements
    //you could use new PriorityQueue<>(A.subList(0, B)) for an O(k) build instead of O(k log k), making it slightly faster.
    private static Integer findKthLargestv1(ArrayList<Integer> A, int B) {
        if (A.size() < B) {
            return null;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(A.subList(0, B)); // O(k) heap build
        for (int i = B; i < A.size(); i++) {
            if (A.get(i) > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(A.get(i));
            }
        }
        return minHeap.peek();
    }

    // using max heap
    //SC: O(N)
    //Time: O(N*log(N))
    private static Integer findKthLargestv2(ArrayList<Integer> A, int B) {
        if (A.size() < B) {
            return null;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(A); // still O(n log n) — reverseOrder comparator prevents O(n) constructor path
        for (int i = 0; i < B - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }
}
