package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeakElement {

    public static void main(String[] args) {
        //1, 2, 3, 4, 5
        //5, 17, 100, 11
        //5,4,3,2,1
        List<Integer> A = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        System.out.println(findPeakInArrayv1(A));
        System.out.println(findPeakInArrayv2(A));
        System.out.println(findPeakInArrayv3(A));

        //38,102,135,142,171,178,180,201,229,243,249,270,307,333,455,476,488,491,467,441,427,398,371,360,354,351,343,325,265,226,197,167,164,113,105,93,84,72,61,14,9,2
    }

    //working code
    private static Integer findPeakInArrayv3(List<Integer> input) {
        int len = input.size();
        int low = 0;
        int high = len - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (input.get(mid) < input.get(mid + 1)) { //mid+1 will not go out of bounds as low < high
                low = mid + 1; // Search in the right half
            } else {
                high = mid; // Search in the left half
            }
        }
        return input.get(low);
    }

    private static Integer findPeakInArrayv2(List<Integer> input) {
        int len = input.size();
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid - 1 >= 0 &&
                    input.get(mid) > input.get(mid - 1) &&
                    mid + 1 < len &&
                    input.get(mid) > input.get(mid + 1)) {
                return input.get(mid); // Found a peak
            } else {
                if (mid + 1 < len && input.get(mid) < input.get(mid + 1)) {
                    low = mid + 1; // Search in the right half
                } else {
                    high = mid - 1; // Search in the left half
                }
            }
        }
        return input.get(low);
    }

    private static Integer findPeakInArrayv1(List<Integer> input) {
        int len = input.size();
        if (len == 1) {
            return input.get(0);
        }
        if (input.get(0) > input.get(1)) {
            return input.get(0);
        }
        if (input.get(len - 1) > input.get(len - 2)) {
            return input.get(len - 1);
        }
        int mid = 0;
        int low = 1;
        int high = len - 2;
        int prev = 0;
        int curr = 0;
        int next = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            prev = input.get(mid - 1);
            curr = input.get(mid);
            next = input.get(mid + 1);
            if (curr > prev && curr > next) {
                return curr;
            }
            if (curr < prev) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return input.get(low);
    }

}
