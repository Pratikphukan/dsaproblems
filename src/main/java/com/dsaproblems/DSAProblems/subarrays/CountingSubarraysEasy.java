package com.dsaproblems.DSAProblems.subarrays;

import java.util.ArrayList;
import java.util.Arrays;

public class CountingSubarraysEasy {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 2, 4, 15)); //All numbers are non-negative
        int targetSum = 10;

        System.out.println(subarraySumLessThanKv1(input, targetSum));
        System.out.println(subarraySumLessThanKv2(input, targetSum));
    }

    //for arrays with all non-negative numbers, you can use a sliding window
    // (two pointers) approach to count subarrays with sum less than B in O(n) time
    private static int subarraySumLessThanKv2(ArrayList<Integer> A, int B) {
        int n = A.size();
        int count = 0, sum = 0, start = 0;
        for (int end = 0; end < n; end++) {
            sum += A.get(end);
            while (sum >= B && start <= end) { //If sum becomes >= B, move start forward and subtract from sum until sum < B.
                sum -= A.get(start++);
            }
            count += (end - start + 1); //all subarrays ending at end,
            // for an array of 3 elements, subarrsy ending at 0->1
            // subarrays ending at 1->2
            // subarrys ending at 2->3
        }
        return count;
    }

    //working code
    //O(n^2), where (n) is the size of the input list
    //O(1) extra space
    private static int subarraySumLessThanKv1(ArrayList<Integer> A, int B) {
        int len = A.size();
        int count = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += A.get(j);
                if (sum < B) count++;
                else break; //all positive numbers, adding one more will just increase the value
            }
        }
        return count;
    }
}
