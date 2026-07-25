package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class MinHeap {

    private List<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    // new node might even become the root of the tree
    // TC->O(log(n)), percolate up algorithm
    public void add(Integer item) {
        heap.add(item);
        percolateUp(heap.size() - 1, item);
    }

    //The add() method is already optimal at O(log n)
    private void percolateUp(int childIdx, int value) {
        while (childIdx > 0) {
            int parentIdx = (childIdx - 1) / 2;
            if (heap.get(parentIdx) > value) {
                heap.set(childIdx, heap.get(parentIdx));
                childIdx = parentIdx;
            } else {
                break;
            }
        }
        heap.set(childIdx, value);
    }

    //The poll() method is time-optimal at O(log n)
    public Integer poll() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);

        int min = heap.get(0);
        int parent = heap.remove(heap.size() - 1); //basically it replaces the element at 0th index
        percolateDown(0, heap.size(), parent);
        return min;
    }

    private void percolateDown(int idx, int size, int parent) {
        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = left;

            if (right < size && heap.get(right) < heap.get(left)) {
                smallest = right;
            }

            if (heap.get(smallest) < parent) {
                heap.set(idx, heap.get(smallest));
                idx = smallest;
            } else {
                break;
            }
        }
        heap.set(idx, parent);
    }

    // last node will come to the start and percolate down
//    public Integer poll() {
//        if (heap.size() == 1) {
//            return heap.remove(0);
//        }
//        int startIdx = 0;
//        int endIdx = heap.size() - 1;
//        int temp = heap.get(startIdx);
//        heap.set(startIdx, heap.get(endIdx));
//        heap.set(endIdx, temp);
//        int min = heap.remove(endIdx);
//        while (startIdx < heap.size()) { // heap size won't change, only elements will rearrange
//            int parentIdx = startIdx;
//            int left = 2 * parentIdx + 1;
//            int right = 2 * parentIdx + 2;
//            if (left < heap.size() && heap.get(left) < heap.get(parentIdx)) {
//                parentIdx = left;
//            }
//            if (right < heap.size() && heap.get(right) < heap.get(parentIdx)) {
//                parentIdx = right;
//            }
//            if (parentIdx != startIdx) {
//                int t = heap.get(startIdx);
//                heap.set(startIdx, heap.get(parentIdx));
//                heap.set(parentIdx, t);
//                startIdx = parentIdx;
//            } else {
//                break;
//            }
//        }
//        return min;
//    }

    //The minHeapify method is already O(n) (optimal for building a heap from scratch)
    //Benefits:
    //Extract percolate-down logic (reusable in poll() too, reducing duplication)
    //Cleaner loop logic
    //Same O(n) complexity, just cleaner code
    public void minHeapify(List<Integer> input) {
        heap = new ArrayList<>(input);
        int len = heap.size();
        int lastNonLeaf = len / 2 - 1;
        for (int i = lastNonLeaf; i >= 0; i--) {
            percolateDown(i, len);
        }
    }

    private void percolateDown(int idx, int size) {
        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = left;
            if (right < size && heap.get(right) < heap.get(left)) {
                smallest = right;
            }
            if (heap.get(smallest) < heap.get(idx)) {
                swap(idx, smallest);
                idx = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }
}
