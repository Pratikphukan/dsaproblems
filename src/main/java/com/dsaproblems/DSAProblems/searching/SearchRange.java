package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRange {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(4, 7, 7, 7, 8, 10, 10));
        int B = 3;
        // 4, 7, 7, 7, 8, 10, 10|3
        // -5, -5, -3, 0, 0, 1, 1, 5, 5, 5, 5, 5, 5, 5, 8, 10, 10, 15, 15|5
        System.out.println(findStartAndEndOfRange(A, B));
        System.out.println(findStartAndEndOfRangev1(A, B));
    }

    //working code
    private static List<Integer> findStartAndEndOfRangev1(List<Integer> input, int B) {
        int start = findFirstOccurrencev1(input, B);
        if (start == -1) {
            return new ArrayList<>(Arrays.asList(-1, -1));
        }
        return new ArrayList<>(Arrays.asList(start, findLastOccurrencev1(input, B, start)));
    }

    private static Integer findLastOccurrencev1(List<Integer> input, int B, int start) {
        int low = start;
        int high = input.size() - 1;
        int end = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                end = mid;
                low = mid + 1;
            }
        }
        return end;
    }

    private static int findFirstOccurrencev1(List<Integer> input, int B) {
        int low = 0;
        int high = input.size() - 1;
        int start = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                start = mid;
                high = mid - 1;
            }
        }
        return start;
    }

    private static List<Integer> findStartAndEndOfRange(List<Integer> input, int B) {
        List<Integer> ans = new ArrayList<>();
        int low = 0;
        int high = input.size() - 1;
        int mid = 0;
        int start = -1;
        int end = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                start = mid;
                high = mid - 1;
            }
        }
        low = 0;
        high = input.size() - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                end = mid;
                low = mid + 1;
            }
        }
        ans.add(start);
        ans.add(end);
        return ans;
    }

}
