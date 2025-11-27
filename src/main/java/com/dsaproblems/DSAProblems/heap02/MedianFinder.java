package com.dsaproblems.DSAProblems.heap02;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    private final Queue<Integer> maxHeap;
    private final Queue<Integer> minHeap;
    private double currMedian;

    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
    }

    public void addNumv1(int num) {
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        while (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
        while (minHeap.size() - maxHeap.size() >= 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedianv1() {
        if (minHeap.size() != maxHeap.size()) {
            return (double) maxHeap.peek();
        } else {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        }
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            this.currMedian = num;
        } else {
            if (minHeap.size() == maxHeap.size()) {
                if (num < this.currMedian) {
                    maxHeap.offer(num);
                    this.currMedian = maxHeap.peek();
                } else {
                    minHeap.offer(num);
                    this.currMedian = minHeap.peek();
                }
            } else {
                if (maxHeap.size() > minHeap.size()) {
                    if (num < this.currMedian) {
                        minHeap.offer(maxHeap.poll());
                        maxHeap.offer(num);
                    } else {
                        minHeap.offer(num);
                    }
                } else {
                    if (num > this.currMedian) {
                        maxHeap.offer(minHeap.poll());
                        minHeap.offer(num);
                    } else {
                        maxHeap.offer(num);
                    }
                }
                this.currMedian = ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
            }
        }
    }

    public double findMedian() {
        return this.currMedian;
    }
}
