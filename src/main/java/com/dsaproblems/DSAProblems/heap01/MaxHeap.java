package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class MaxHeap {

    private List<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    // new node might even become the root of the tree
    public void add(Integer item) {
        heap.add(item);
        int childIdx = heap.size() - 1; // current index
        int value = item;
        while (childIdx > 0) {
            int parentIdx = (childIdx - 1) / 2;
            if (heap.get(parentIdx) < value) {
                heap.set(childIdx, heap.get(parentIdx));
                childIdx = parentIdx;
            } else {
                break;
            }
        }
        heap.set(childIdx, value); // set the value at the correct position
    }

    //The poll() here is already optimal — same pattern as MinHeap.poll(), O(log n)
    public Integer poll() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);

        int max = heap.get(0);
        int parent = heap.remove(heap.size() - 1);
        percolateDown(0, heap.size(), parent);
        return max;
    }

    private void percolateDown(int idx, int size, int parent) {
        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int largest = left;

            if (right < size && heap.get(right) > heap.get(left)) {
                largest = right;
            }
            if (heap.get(largest) > parent) {
                heap.set(idx, heap.get(largest));
                idx = largest;
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
//        int max = heap.remove(endIdx);
//        while (startIdx < heap.size()) { // heap size won't change, only elements will rearrange
//            int parentIdx = startIdx;
//            int left = 2 * parentIdx + 1;
//            int right = 2 * parentIdx + 2;
//            if (left < heap.size() && heap.get(left) > heap.get(parentIdx)) {
//                parentIdx = left;
//            }
//            if (right < heap.size() && heap.get(right) > heap.get(parentIdx)) {
//                parentIdx = right;
//            }
//            if (parentIdx != startIdx) {
//                int t = heap.get(startIdx);
//                heap.set(startIdx, heap.get(parentIdx));
//                heap.set(parentIdx, t);
//                startIdx = parentIdx;// this is necessary if minidx==idx
//            } else {
//                break;
//            }
//        }
//        return max;
//    }

    /*
    O(n) — here's the intuition:
    The loop runs from len/2 - 1 down to 0 (only non-leaf nodes).
    Each node's percolate-down cost depends on its height, not log n.
    Total work = n/4 * 1 + n/8 * 2 + n/16 * 3 + ...
    This summation converges to O(n).
    Why not O(n log n)?
    That would be the cost if you built the heap by calling
    add() n times (percolate-up for each). maxHeapify is smarter — most nodes are near the bottom and do very little work.
     */
    public void maxHeapify(List<Integer> input) {
        heap = new ArrayList<>(input);
        int len = heap.size();
        for (int i = (len / 2 - 1); i >= 0; i--) {
            percolateDown(i, len, heap.get(i));
        }
    }
}
