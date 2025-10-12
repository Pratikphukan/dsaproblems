package com.dsaproblems.DSAProblems.searching;

public class SquareRootOfInteger {

    public static void main(String[] args) {
        System.out.println(findSquareRootv1(2147483647));
    }

    private static int findSquareRootv1(int A) {
        if (A == 0) {
            return 0;
        }
        long low = 1;
        long high = A;
        long ans = 1L;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid > A) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return (int) ans;
    }

}
