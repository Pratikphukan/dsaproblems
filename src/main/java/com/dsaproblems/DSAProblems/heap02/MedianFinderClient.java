package com.dsaproblems.DSAProblems.heap02;

public class MedianFinderClient {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 6};
        MedianFinder medianFinder = new MedianFinder();
        for (int num : nums) {
            medianFinder.addNumv1(num);
            System.out.println(medianFinder.findMedianv1());
        }
    }
}
