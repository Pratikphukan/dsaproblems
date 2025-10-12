package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(10, 7, 11, 30, 20, 38, 2, 45));

        System.out.println(input);

        MinHeap minHeap = new MinHeap();

        minHeap.minHeapify(input);

        System.out.println(minHeap.getHeap());

        minHeap.getHeap().clear();

        for (Integer item : input) {
            minHeap.add(item);
        }

        System.out.println(minHeap.getHeap());

        System.out.println(minHeap.poll());

        System.out.println(minHeap.getHeap());

        System.out.println(minHeap.poll());

        System.out.println(minHeap.getHeap());

        System.out.println(minHeap.poll());

        System.out.println(minHeap.getHeap());

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.maxHeapify(input);

        System.out.println(maxHeap.getHeap());

        maxHeap.getHeap().clear();

        for (Integer item : input) {
            maxHeap.add(item);
        }

        System.out.println(maxHeap.getHeap());

        System.out.println(maxHeap.poll());

        System.out.println(maxHeap.getHeap());

        System.out.println(maxHeap.poll());

        System.out.println(maxHeap.getHeap());

        System.out.println(maxHeap.poll());

        System.out.println(maxHeap.getHeap());

        System.out.println(input);
    }
}
