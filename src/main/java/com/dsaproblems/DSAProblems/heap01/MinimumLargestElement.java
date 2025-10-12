package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumLargestElement {

    public static void main(String[] args) {
        // 8,6,4,2|8
        // 1, 2, 3, 4|3
        // 1|1
        /*
         * 6257,7396,6462,8360,2553,4838,6403,3742,12,456,9748,5820,9849,2977,557,9248,
         * 5435,3030,6891,7806,7039,8280,8665,3028,17,9070,6592,1902,7548,919,233,156,
         * 4666,6694,8515,7218,7883,4918,960,7894,1725,7059,3713,7925,35,621,3524,5469,
         * 3651,414,9627,7041,5046,8291,69,1414,7360,3012,9668,4908,3930,9900,5063,4947,
         * 2945,9930,2165,7179,1199,9476,1425,2923,6534,1489,7199,6568,2110,722,8388,
         * 5760,7488,8014,9152,8885,6305,5572,298,16,8583,6317,1275,8864,2568,2690,3811,
         * 5512,2619,5975,9043,169|61802
         *
         * [6257,7396,6462,8360,2553,4838,6403,3742,12,456,9748,5820,9849,2977,557,9248,
         * 5435,3030,6891,7806,7039,8280,8665,3028,17,9070,6592,1902,7548,919,233,156,
         * 4666,6694,8515,7218,7883,4918,960,7894,1725,7059,3713,7925,35,621,3524,5469,
         * 3651,414,9627,7041,5046,8291,69,1414,7360,3012,9668,4908,3930,9900,5063,4947,
         * 2945,9930,2165,7179,1199,9476,1425,2923,6534,1489,7199,6568,2110,722,8388,
         * 5760,7488,8014,9152,8885,6305,5572,298,16,8583,6317,1275,8864,2568,2690,3811,
         * 5512,2619,5975,9043,169]
         */
        //-2,-4,-8,-16|10
        List<Integer> A = new ArrayList<>(Arrays.asList(-2, -4, -8, -16));
        int B = 10;
        System.out.println(findMinimumLargestElementv1(A, B));
        System.out.println(findMinimumLargestElementv2(A, B));
        System.out.println(findMinimumLargestElementv3(A, B));
    }

    //working code
    private static int findMinimumLargestElementv3(List<Integer> input, int B) {
        Queue<Item> minHeap = new PriorityQueue<>(new ItemComparator());
        for (int item : input) {
            minHeap.add(new Item(item, item));
        }
        Item min = null;
        for (int i = 1; i <= B; i++) {
            min = minHeap.poll();
            min.setModifiedValue(min.getModifiedValue() + min.getOriginalValue());
            minHeap.offer(min);
        }
        int maxElement = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            maxElement = Math.max(maxElement, minHeap.poll().getModifiedValue());
        }
        return maxElement;
    }

    // working code
    private static int findMinimumLargestElementv2(List<Integer> input, int B) {
        Queue<Item> minHeap = new PriorityQueue<>();
        for (int item : input) {
            minHeap.add(new Item(item, item));
        }
        Item min = null;
        if (minHeap.size() == 1) {
            while (B-- > 0) {
                min = minHeap.poll();
                min.setModifiedValue(min.getOriginalValue() + min.getModifiedValue());
                minHeap.add(min);
            }
            return minHeap.poll().getModifiedValue();
        }
        while (B-- > 0) {
            min = minHeap.poll();
            min.setModifiedValue(min.getOriginalValue() + min.getModifiedValue());
            minHeap.add(min);
        }
        int maxElement = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            maxElement = Math.max(maxElement, minHeap.poll().getModifiedValue());
        }
        return maxElement;
    }

    // partially working code
    private static int findMinimumLargestElementv1(List<Integer> input, int B) {
        Queue<Element> minHeap = new PriorityQueue<>();
        for (int item : input) {
            minHeap.add(new Element(item, item));
        }
        Element firstMin = null;
        Element secondMin = null;
        if (minHeap.size() == 1) {
            while (B-- > 0) {
                firstMin = minHeap.poll();
                firstMin.setModifiedValue(firstMin.getOriginalValue() + firstMin.getModifiedValue());
                minHeap.add(firstMin);
            }
            return minHeap.poll().getModifiedValue();
        }
        while (B-- > 0) {
            firstMin = minHeap.poll();
            secondMin = minHeap.poll();
            if ((firstMin.getOriginalValue() + firstMin.getModifiedValue()) > (secondMin.getOriginalValue()
                    + secondMin.getModifiedValue())) {
                minHeap.add(firstMin);
                secondMin.setModifiedValue(secondMin.getOriginalValue() + secondMin.getModifiedValue());
                minHeap.add(secondMin);
            } else {
                minHeap.add(secondMin);
                firstMin.setModifiedValue(firstMin.getOriginalValue() + firstMin.getModifiedValue());
                minHeap.add(firstMin);
            }
        }
        Element maxElement = null;
        while (!minHeap.isEmpty()) {
            maxElement = minHeap.poll();
        }
        return maxElement.getModifiedValue();
    }

}
