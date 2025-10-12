package com.dsaproblems.DSAProblems.subarrays;

import java.util.ArrayList;
import java.util.List;

public class SubarrayWithLeastAverage {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(List.of(3, 7, 90, 20, 10, 50, 40));
        int k = 3;
        System.out.println(leastAverageSubarrayv1(A, k));
    }

    //working code
    private static int leastAverageSubarrayv1(ArrayList<Integer> A, int k) {
        int sum = 0;
        int i = 0;
        for (; i < k; i++) {
            sum += A.get(i);
        }
        int minSum = sum;
        int start = 0;
        for (i = k; i < A.size(); i++) {
            sum += A.get(i) - A.get(i - k);//Slide the window by adding the next element and removing the first element of the previous window.
            if (sum < minSum) {
                minSum = sum;
                start = i - k + 1;
            }
        }
        return start;
    }
}
