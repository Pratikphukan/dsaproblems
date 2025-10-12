package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 5, 4, 3));

        System.out.println(findMaxAreav1(input));
        System.out.println(findMaxAreav2(input));
    }

    //working code
    //input has only non-negative integers
    private static int findMaxAreav2(List<Integer> A) {
        if (A == null) return 0;
        int n = A.size();
        int first = 0, last = n - 1, area = 0;
        while (first < last) {
            int width = last - first;
            // finds the area for the current window
            area = Math.max(area, Math.min(A.get(first), A.get(last)) * width);
            if (A.get(first) >= A.get(last))
                last--;
            else
                first++;
        }
        return area;
    }

    //working code
    public static int findMaxAreav1(List<Integer> A) {
        int l = 0, h = A.size() - 1, max = 0;
        while (l < h) {
            int height = Math.min(A.get(l), A.get(h));
            int width = h - l;
            max = Math.max(max, height * width);
            if (A.get(l) > A.get(h)) {
                h--;
            } else if (A.get(l) < A.get(h)) {
                l++;
            } else {
                l++;
                h--;
            }
        }
        return max;
    }
}
