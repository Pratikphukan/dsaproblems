package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixMedian {
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 3, 5));
        input.add(Arrays.asList(2, 6, 9));
        input.add(Arrays.asList(3, 6, 9));
        System.out.println(findMatrixMedianv1(input));
    }

    private static int findMatrixMedianv1(List<List<Integer>> input) {
        //find the index where you will find the median
        int r = input.size(), c = input.get(0).size();
        int medianIdx = r * c / 2;
        int low = 100_000, high = 1;
        for (List<Integer> nums : input) {
            low = Math.min(low, nums.get(0));
            high = Math.max(high, nums.get(c - 1));
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            //count elements less than mid for each row
            int count = getCountOfNumsLessThanOrEqualMid(input, mid);
            if (medianIdx < count) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int getCountOfNumsLessThanOrEqualMid(List<List<Integer>> input, int mid) {
        int count = 0;
        for (List<Integer> nums : input) {
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
        }
        return count;
    }
}
