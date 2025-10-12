package com.dsaproblems.DSAProblems.searching01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnyPeakElement {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 17, 100, 11));

        System.out.println(findAnyPeakElement(input));
        System.out.println(findAnyPeakElement1(input));
    }

    // whichever side is greater, we will get atleast one peak along that side
    // working code
    //TC: O(log n) — Binary search is used to find a peak.
    //SC: O(1) — No extra space except variables.
    private static Integer findAnyPeakElement(List<Integer> A) {
        if (A.size() == 1) {
            return A.get(0);
        }
        int len = A.size();
        if (A.get(0) >= A.get(1)) {
            return A.get(0);
        }
        if (A.get(len - 2) <= A.get(len - 1)) {
            return A.get(len - 1);
        }
        int l = 1, h = len - 2;
        while (l < h) {
            int mid = h - (h - l) / 2;
            if (A.get(mid - 1) < A.get(mid) && A.get(mid) > A.get(mid + 1)) {
                return A.get(mid);
            }
            if (A.get(mid) < A.get(mid + 1)) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return A.get(h);
    }

    //working code
    //TC: O(log n) — Also uses binary search.
    //SC: O(1) — Only uses a few variables.
    private static Integer findAnyPeakElement1(List<Integer> A) {
        if (A.size() == 1) {
            return A.get(0);
        }
        int len = A.size();
        if (A.get(0) >= A.get(1)) {
            return A.get(0);
        }
        if (A.get(len - 2) <= A.get(len - 1)) {
            return A.get(len - 1);
        }
        int l = 1, h = len - 2;
        while (l < h) {
            int mid = h - (h - l) / 2;
            if (A.get(mid - 1) < A.get(mid) && A.get(mid) > A.get(mid + 1)) {
                return A.get(mid);
            }
            if (A.get(mid - 1) > A.get(mid)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return A.get(l);
    }

}
