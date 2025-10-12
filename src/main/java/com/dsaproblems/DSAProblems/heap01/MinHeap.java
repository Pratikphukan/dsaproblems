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
        int childIdx = heap.size() - 1; // current index
        int value = item;
        while (childIdx > 0) {
            int parentIdx = (childIdx - 1) / 2;
            if (heap.get(parentIdx) > value) {
                heap.set(childIdx, heap.get(parentIdx));
                childIdx = parentIdx;
            } else {
                break; // cover at most the height of the complete BT, TC->O(log(n))
            }
        }
        heap.set(childIdx, value); // set the value at the correct position
    }

    public Integer poll() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);

        int min = heap.get(0);
        int value = heap.remove(heap.size() - 1);
        int idx = 0;
        int size = heap.size();

        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = left;

            if (right < size && heap.get(right) < heap.get(left)) {
                smallest = right;
            }

            if (heap.get(smallest) < value) {
                heap.set(idx, heap.get(smallest));
                idx = smallest;
            } else {
                break;
            }
        }
        heap.set(idx, value);
        return min;
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

    public void minHeapify(List<Integer> input) {
        heap = new ArrayList<>(input);
        int len = heap.size();
        for (int i = (len / 2 - 1); i >= 0; i--) {
            int startIdx = i;
            while (startIdx < len / 2) {
                int parentIdx = startIdx;
                int left = 2 * parentIdx + 1;
                int right = 2 * parentIdx + 2;
                if (left < len && heap.get(left) < heap.get(parentIdx)) {
                    parentIdx = left;
                }
                if (right < len && heap.get(right) < heap.get(parentIdx)) {
                    parentIdx = right;
                }
                if (parentIdx != startIdx) {
                    int t = heap.get(startIdx);
                    heap.set(startIdx, heap.get(parentIdx));
                    heap.set(parentIdx, t);
                    startIdx = parentIdx; // this is necessary if startIdx == parentIdx
                } else {
                    break;
                }
            }
        }
    }

}
