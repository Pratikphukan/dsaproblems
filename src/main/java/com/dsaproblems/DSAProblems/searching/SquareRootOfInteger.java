package com.dsaproblems.DSAProblems.searching;

public class SquareRootOfInteger {

    public static void main(String[] args) {
        System.out.println(findSquareRootv1(2147483647));
        System.out.println(findSquareRootv1(37));
        System.out.println(findSquareRootv2(2147483647));
        System.out.println(findSquareRootv2(37));
    }

    private static int findSquareRootv2(int A) {
        if (A == 0) {
            return 0;
        }
        int low = 1, high = A, root = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == A / mid && A % mid == 0)
                return mid;
            if (mid > A / mid) {
                high = mid - 1;
            } else {
                root = mid;
                low = mid + 1;
            }
        }
        return root;
    }

    private static int findSquareRootv1(int A) {
        if (A == 0) {
            return 0;
        }
        long low = 1, high = A, ans = 1L;
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
