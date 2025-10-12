package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReversePairs {

    static class Element implements Comparable<Element> {

        int idx;

        int val;

        Element(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Element element) {
            if (this.val == element.val) {
                return this.idx - element.idx; // sort by index if values are equal
            }
            return this.val - element.val; // sort by value
        }
    }

    public static void main(String[] args) {

        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 2, 3, 1));

        System.out.println(reversePairsv1(input));
        //System.out.println(reversePairsv2(input));
    }

    private static int reversePairsv1(List<Integer> input) {
        return mergesort(input, 0, input.size() - 1);
    }

    private static int mergesort(List<Integer> C, int s, int e) {
        if (s >= e) {
            return 0;
        }
        int mid = (s + e) / 2;
        int res = mergesort(C, s, mid) + mergesort(C, mid + 1, e);
        for (int i = s, j = mid + 1; i <= mid; i++) {
            while (j <= e && (long) C.get(i) > 2 * (long) C.get(j)) {
                j++;
            }
            res += j - mid - 1;
        }
        merge(C, s, mid, e);
        return res;
    }

    private static void merge(List<Integer> B, int s, int m, int e) {
        int i = s, j = m + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        while (i <= m && j <= e) { // mistake is considering the size
            if (B.get(i) < B.get(j)) {
                ans.add(B.get(i));
                i++;
            } else {
                ans.add(B.get(j));
                j++;
            }
        }
        while (i <= m) {
            ans.add(B.get(i));
            i++;
        }
        while (j <= e) {
            ans.add(B.get(j));
            j++;
        }
        // we got the sorted data in the temporary array
        for (int k = 0; k < (e - s + 1); k++) {
            B.set(s + k, ans.get(k));
        }
    }
}
