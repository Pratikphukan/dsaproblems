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

    public Integer poll() {
        if (heap.isEmpty()) return null;
        if (heap.size() == 1) return heap.remove(0);

        int max = heap.get(0);
        int value = heap.remove(heap.size() - 1);
        int idx = 0;
        int size = heap.size();

        while (idx < size / 2) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;

            int largest = left;
            if (right < size && heap.get(right) > heap.get(left)) {
                largest = right;
            }

            if (heap.get(largest) > value) {
                heap.set(idx, heap.get(largest));
                idx = largest;
            } else {
                break;
            }
        }

        heap.set(idx, value);
        return max;
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

    public void maxHeapify(List<Integer> input) {
        heap = new ArrayList<>(input);
        int len = heap.size();
        for (int i = (len / 2 - 1); i >= 0; i--) { // percolate down approach
            int startIdx = i;
            while (startIdx < len / 2) {
                int parentIdx = startIdx;
                int left = 2 * parentIdx + 1;
                int right = 2 * parentIdx + 2;
                if (left < len && heap.get(left) > heap.get(parentIdx)) { // take care that it is not arr[idx]>arr[l]
                    parentIdx = left;
                }
                if (right < len && heap.get(right) > heap.get(parentIdx)) {
                    parentIdx = right;
                }
                if (parentIdx != startIdx) {
                    int t = heap.get(startIdx);
                    heap.set(startIdx, heap.get(parentIdx));
                    heap.set(parentIdx, t);
                    startIdx = parentIdx;
                } else {
                    break;// this is necessary if startIdx == parentIdx
                }
            }
        }
    }

}
