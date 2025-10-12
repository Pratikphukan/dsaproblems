package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountPairsWithGivenSum {

    //input is sorted, distinct and positive integers
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 10, 20, 100, 105)); // distinct and sorted integers
        int targetSum = 110;
        findCountPairsWithGivenSum(input, targetSum);
    }

    //O(n) time and O(1) space
    private static int findCountPairsWithGivenSum(List<Integer> input, int targetSum) {
        int count = 0;
        int low = 0;
        int high = input.size() - 1;
        while (low < high) {
            if ((input.get(low) + input.get(high)) == targetSum) {
                count++;
                low++;
                high--;
            } else if ((input.get(low) + input.get(high)) > targetSum) {
                high--;
            } else {
                low++;
            }
        }
        return count;
    }

}
