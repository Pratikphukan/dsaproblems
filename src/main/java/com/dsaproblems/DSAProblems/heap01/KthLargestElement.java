package com.dsaproblems.DSAProblems.heap01;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {
        List<Integer> a = List.of(10, 7, 11, 5, 27, 8, 20, 45);
        ArrayList<Integer> A = new ArrayList<Integer>(a);
        if (findKthLargest1(A, 3) == null) {
            System.out.println("Invalid case");
        } else {
            System.out.println(findKthLargest1(A, 3));
        }
        if (findKthLargest2(A, 3) == null) {
            System.out.println("Invalid case");
        } else {
            System.out.println(findKthLargest2(A, 3));
        }
    }

    // using min heap
    private static Integer findKthLargest1(ArrayList<Integer> A, int B) {
        if (A.size() < B) {
            return null;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int i = 0; i < B; i++) {
            minHeap.add(A.get(i));
        }
        for (int i = B; i < A.size(); i++) {
            if (A.get(i) > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(A.get(i));
            }
        }
        return minHeap.peek();
    }

    // using max heap
    private static Integer findKthLargest2(ArrayList<Integer> A, int B) {
        if (A.size() < B) {
            return null;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < A.size(); i++) {
            maxHeap.add(A.get(i));
        }
        for (int i = 0; i < B - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }

}
