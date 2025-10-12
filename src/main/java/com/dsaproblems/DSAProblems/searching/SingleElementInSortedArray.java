package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleElementInSortedArray {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3));
        // 13, 13, 21, 21, 27, 50, 50, 102, 102, 108, 108, 110, 110, 117,
        //                117, 120, 120, 123, 123, 124, 124, 132, 132, 164, 164, 166, 166, 190, 190, 200, 200, 212, 212, 217, 217,
        //                225, 225, 238, 238, 261, 261, 276, 276, 347, 347, 348, 348, 386, 386, 394, 394, 405, 405, 426, 426, 435,
        //                435, 474, 474, 493, 493
        System.out.println(findTheSingleElementInSortedArrayv1(A));
        System.out.println(findTheSingleElementInSortedArrayv2(A));
        System.out.println(findTheSingleElementInSortedArrayv3(A));
        System.out.println(findTheSingleElementInSortedArrayv4(A));
    }

    private static Integer findTheSingleElementInSortedArrayv4(List<Integer> input) {
        int low = 0, high = input.size() - 1, mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (mid % 2 == 1) mid--; // Ensure mid is even
            if (input.get(mid).equals(input.get(mid + 1))) {
                low = mid + 2;
            } else {
                high = mid - 1;
            }
        }
        return input.get(low);
    }

    //working code
    private static Integer findTheSingleElementInSortedArrayv3(List<Integer> input) {
        int low = 0, high = input.size() - 1, mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (mid % 2 == 1) mid--; // Ensure mid is even
            if (input.get(mid).equals(input.get(mid + 1))) {
                low = mid + 2;
            } else {
                high = mid - 1;
            }
        }
        return input.get(low);
    }

    //working code
    private static Integer findTheSingleElementInSortedArrayv2(List<Integer> input) {
        int len = input.size();
        if (len == 1) {
            return input.get(0);
        }
        if (!input.get(0).equals(input.get(1))) {
            return input.get(0);
        }
        if (!input.get(len - 2).equals(input.get(len - 1))) {
            return input.get(len - 1);
        }
        int low = 0, high = len - 1, mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            int prev = input.get(mid - 1);
            int curr = input.get(mid);
            int next = input.get(mid + 1);
            if (curr != prev && curr != next) {
                return curr;
            }
            if (curr == next) {
                mid++; // move to the second occurrence
            }
            if (mid % 2 == 0) {
                high = mid - 2;
            } else {
                low = mid + 1;
            }
        }
        return input.get(mid);
    }

    //working code
    private static Integer findTheSingleElementInSortedArrayv1(List<Integer> input) {
        int len = input.size();
        if (len == 1) {
            return input.get(0);
        }
        if (!input.get(0).equals(input.get(1))) {
            return input.get(0);
        }
        if (!input.get(len - 2).equals(input.get(len - 1))) {
            return input.get(len - 1);
        }
        int low = 0, high = len - 1, mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            int prev = input.get(mid - 1);
            int curr = input.get(mid);
            int next = input.get(mid + 1);
            if (curr != prev && curr != next) {
                return curr;
            }
            if (curr == prev) {
                mid--; // move to the first occurrence
            }
            if (mid % 2 == 0) {
                low = mid + 2;
            } else {
                high = mid - 1;
            }
        }
        return input.get(mid);
    }

}
