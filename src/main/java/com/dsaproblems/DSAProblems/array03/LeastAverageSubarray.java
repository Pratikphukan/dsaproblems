package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;

public class LeastAverageSubarray {

    public static void main(String[] args) {
        // (3, 7, 90, 20, 10, 50, 40), (-1,4,7,6,-2,7,8,10), k = 3
        // (20, 3, 13, 5, 10, 14, 8, 5, 11, 9, 1, 11), 9
        ArrayList<Integer> A = new ArrayList<>(List.of(3, 7, 90, 20, 10, 50, 40));
        int k = 3;
        System.out.println(leastAverageSubarrayv1(A, k));
        System.out.println(leastAverageSubarrayv2(A, k));
    }

    private static int leastAverageSubarrayv2(ArrayList<Integer> A, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += A.get(i);
        }
        int minSum = sum;
        int index = 0;
        for (int i = k; i < A.size(); i++) {
            sum += (A.get(i) - A.get(i - k));
            if (sum < minSum) {
                index = i - k + 1;
                minSum = sum;
            }
        }
        return index;
    }

    //time complexity (O(n)) and space complexity (O(1))
    private static int leastAverageSubarrayv1(ArrayList<Integer> A, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += A.get(i);
        }
        int min = sum;
        int index = 0;
        for (int i = 1; i <= A.size() - k; i++) {
            sum += (A.get(i + k - 1) - A.get(i - 1));
            if (sum < min) {
                index = i;
                min = sum;
            }
        }
        return index;
    }

}
