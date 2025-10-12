package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FoodPacketsDistribution {

    public static void main(String[] args) {
        //50, 150, 100
        //7
        List<Integer> A = new ArrayList<>(Arrays.asList(5, 25, 6));
        int offices = 3;
        System.out.println(findPeakInArray(A, offices));
        System.out.println(findPeakInArrayv1(A, offices));
    }

    private static int findPeakInArrayv1(List<Integer> candies, int k) {
        int maxCandiesInPile = 0;
        for (Integer candy : candies) {
            maxCandiesInPile = Math.max(maxCandiesInPile, candy);
        }

        // Set the initial search range for binary search
        int left = 0;
        int right = maxCandiesInPile;

        // Binary search to find the maximum number of candies each child can get
        while (left < right) {
            // Calculate the middle value of the current range
            int middle = (left + right + 1) / 2;

            // Check if it's possible to allocate candies so that each child gets 'middle' candies
            if (canAllocateCandies(candies, k, middle)) {
                // If possible, move to the upper half to search for a larger number
                left = middle;
            } else {
                // Otherwise, move to the lower half
                right = middle - 1;
            }
        }

        return left;
    }

    private static boolean canAllocateCandies(List<Integer> candies, int k, int numOfCandies) {
        long maxNumOfChildren = 0;

        // Iterate over all piles to calculate how many children each pile can serve
        for (Integer candy : candies) {
            maxNumOfChildren += candy / numOfCandies;
        }

        return maxNumOfChildren >= k;
    }

    private static int findPeakInArray(List<Integer> A, int offices) {
        long total = 0l;
        int minInCity = 1000000;
        for (int i = 0; i < A.size(); i++) {
            total += A.get(i);
            minInCity = minInCity > A.get(i) ? A.get(i) : minInCity;
        }
        if (total < offices) {
            return 0;
        }
        int min = 1;
        int max = minInCity;
        int mid = 0;
        int ans = 1;
        while (min <= max) {
            mid = min + (max - min) / 2;
            if (check(mid, A) >= offices) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    private static long check(int mid, List<Integer> A) {
        int factorSum = 0;
        for (int i = 0; i < A.size(); i++) {
            factorSum += A.get(i) / mid;
        }
        return factorSum;
    }

}
