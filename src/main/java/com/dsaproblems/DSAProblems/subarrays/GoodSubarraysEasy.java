package com.dsaproblems.DSAProblems.subarrays;

import java.util.ArrayList;
import java.util.Arrays;

public class GoodSubarraysEasy {

    public static void main(String[] args) {
        ///1, 2, 3, 4, 5|4
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)); //All numbers are non-negative
        int targetSum = 4;

        System.out.println(goodSubarraysCountv1(input, targetSum));
    }

    private static int goodSubarraysCountv1(ArrayList<Integer> A, int B) {
        int len = A.size();
        int count = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += A.get(j);
                if (((j - i + 1) % 2 == 0 && sum < B) ||
                        ((j - i + 1) % 2 == 1 && sum > B)) {
                    count++;
                }
            }
        }
        return count;
    }
}
