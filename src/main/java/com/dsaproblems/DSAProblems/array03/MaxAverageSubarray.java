package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;

public class MaxAverageSubarray {

    public static void main(String[] args) {
        // (3, 7, 90, 20, 10, 50, 40), (-1,4,7,6,-2,7,8,10), k = 3
        // (20, 3, 13, 5, 10, 14, 8, 5, 11, 9, 1, 11), 9
        ArrayList<Integer> A = new ArrayList<>(List.of(3, 7, 90, 20, 10, 50, 40));
        int k = 3;
        System.out.println(maxAverageSubarrayv1(A, k));
        System.out.println(maxAverageSubarrayv2(A, k));
    }

    private static int maxAverageSubarrayv2(ArrayList<Integer> A, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += A.get(i);
        }
        int maxSum = sum;
        int index = 0;
        for (int i = k; i < A.size(); i++) {
            sum += (A.get(i) - A.get(i - k));
            if (sum > maxSum) {
                index = i - k + 1;
                maxSum = sum;
            }
        }
        return index;
    }

    private static int maxAverageSubarrayv1(ArrayList<Integer> A, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += A.get(i);
        }
        int maxSum = sum;
        int index = 0;
        for (int i = 1; i <= A.size() - k; i++) {
            sum += (A.get(i + k - 1) - A.get(i - 1));
            if (sum > maxSum) {
                index = i;
                maxSum = sum;
            }
        }
        return index;
    }
}
