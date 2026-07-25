package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.List;

public class HeapSort {
    public static void main(String[] args) {
        List<Integer> a = List.of(8, 2, 6, 1, 5, 7, 4);
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(heapSortv1(A));
        System.out.println(heapSortv2(A));
    }

    //O(n log n) time, O(1) extra space (in-place)
    private static ArrayList<Integer> heapSortv2(ArrayList<Integer> A) {
        maxHeapify(A);
        int len = A.size();
        for (int end = len - 1; end > 0; end--) {
            swap(A, 0, end);          // move current max to sorted suffix
            percolateDownMax(0, end, A);   // restore heap on [0, end)
        }
        return A;
    }

    private static void swap(ArrayList<Integer> A, int i, int j) {
        int t = A.get(i);
        A.set(i, A.get(j));
        A.set(j, t);
    }

    private static void minHeapify(ArrayList<Integer> A) {
        int len = A.size();
        for (int i = (len / 2 - 1); i >= 0; i--) {
            percolateDownMin(i, len, A);
        }
    }

    private static void maxHeapify(ArrayList<Integer> A) {
        int len = A.size();
        for (int i = (len / 2 - 1); i >= 0; i--) {
            percolateDownMax(i, len, A);
        }
    }

    private static void percolateDownMin(int idx, int size, ArrayList<Integer> A) {
        int parent = A.get(idx);
        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = left;

            if (right < size && A.get(right) < A.get(left)) {
                smallest = right;
            }

            if (A.get(smallest) < parent) {
                A.set(idx, A.get(smallest));
                idx = smallest;
            } else {
                break;
            }
        }
        A.set(idx, parent);
    }

    private static void percolateDownMax(int idx, int size, ArrayList<Integer> A) {
        int parent = A.get(idx);
        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int largest = left;

            if (right < size && A.get(right) > A.get(left)) {
                largest = right;
            }
            if (A.get(largest) > parent) {
                A.set(idx, A.get(largest));
                idx = largest;
            } else {
                break;
            }
        }
        A.set(idx, parent);
    }

    //O(n log n) time, O(1) extra space (in-place)
    private static ArrayList<Integer> heapSortv1(ArrayList<Integer> A) { // DESCENDING ORDER
        minHeapify(A);
        int len = A.size();
        for (int end = len - 1; end > 0; end--) {
            swap(A, 0, end);          // move current min to sorted suffix
            percolateDownMin(0, end, A);   // restore heap on [0, end)
        }
        return A;
    }
}
