package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array3Pointers {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 4, 10));
        List<Integer> B = new ArrayList<>(Arrays.asList(2, 15, 20));
        List<Integer> C = new ArrayList<>(Arrays.asList(10, 12));
        System.out.println(minimizev1(A, B, C));
        System.out.println(minimizev2(A, B, C));
    }

    public static int minimizev2(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int n = A.size(), m = B.size(), r = C.size();
        int answer = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0, p3 = 0;
        while (p1 < n && p2 < m && p3 < r) {
            int a = A.get(p1), b = B.get(p2), c = C.get(p3);
            int maxVal = Math.max(a, Math.max(b, c));
            int minVal = Math.min(a, Math.min(b, c));
            answer = Math.min(answer, maxVal - minVal);
            if (answer == 0) return 0;
            // Move the pointer at the minimum value
            if (minVal == a) p1++;
            else if (minVal == b) p2++;
            else p3++;
        }
        return answer;
    }

    //Each term ∣x−y∣ is the distance between two numbers.
    //For three numbers a,b,c, the largest possible distance among
    // them will always be between the smallest and the largest.
    public static int minimizev1(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int n = A.size();
        int m = B.size();
        int r = C.size();
        int answer = Integer.MAX_VALUE;
        int p1 = 0, p2 = 0, p3 = 0;
        while (p1 < n && p2 < m && p3 < r) {
            int temp = Math.max(A.get(p1), Math.max(B.get(p2), C.get(p3))) -
                    Math.min(A.get(p1), Math.min(B.get(p2), C.get(p3)));
            if (answer > temp) {
                answer = temp;
                if (answer == 0) return 0;
            }
            if (A.get(p1) <= B.get(p2) && A.get(p1) <= C.get(p3)) p1++;
            else if (B.get(p2) <= A.get(p1) && B.get(p2) <= C.get(p3)) p2++;
            else p3++;
        }
        return answer;
    }
}
