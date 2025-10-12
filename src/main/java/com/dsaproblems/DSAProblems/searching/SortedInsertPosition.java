package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedInsertPosition {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 9)); // B=11
        // List<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 5, 6));// B=7
        int B = 11;//11,0
        System.out.println(findIndexOfElementInSortedArray(A, B));
        System.out.println(findIndexOfElementInSortedArrayv1(A, B));
        System.out.println(findIndexOfElementInSortedArrayv2(A, B));
    }

    // working
    private static int findIndexOfElementInSortedArrayv2(List<Integer> input, int B) {
        int low = 0;
        int high = input.size() - 1;
        int mid = 0;
        int ans = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                ans = Math.max(ans, mid + 1);
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return ans;
    }

    // working
    private static int findIndexOfElementInSortedArrayv1(List<Integer> input, int B) {
        int low = 0;
        int high = input.size() - 1;
        int mid = 0;
        int ans = high + 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (input.get(mid) > B) {
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return ans;
    }

    // working
    private static int findIndexOfElementInSortedArray(List<Integer> input, int B) {
        int low = 0;
        int high = input.size() - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

}
