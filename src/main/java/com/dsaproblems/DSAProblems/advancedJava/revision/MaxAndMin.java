package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class MaxAndMin {

    public static void main(String[] args) {
        int[] a = {2, 13, 8, 4, 1, 5, 3, 2, 7};
        // (4,3,4,6,2), (2,1,5,6,2,3)
        ArrayList<Integer> A = new ArrayList<>(List.of(2, 13, 8, 4, 1, 5, 3, 2, 7));
        System.out.println(Arrays.toString(nearestGreaterLeftIndices(A)));
        System.out.println(Arrays.toString(nearestGreaterRightIndices(A)));
        System.out.println(Arrays.toString(nearestSmallerLeftIndices(A)));
        System.out.println(Arrays.toString(nearestSmallerRightIndices(A)));
        System.out.println(solvev1(a));
    }

    private static int solvev1(int[] A) {
        //optimized approach
        //TC-O(n),SC-O(n)
        int mod = 1_000_000_007;
        long sum_of_max = 0;
        long sum_of_min = 0;
        int[] nSL = getSmallerNearestLeft(A);
        int[] nSR = getSmallerNearestRight(A);
        int[] nGL = getGreaterNearestLeft(A);
        int[] nGR = getGreaterNearestRight(A);
        //find the next max to right
        //calculate sum of Maximums and Minimums
        for (int i = 0; i < A.length; i++) {
            //((i - nGL[i])) is the number of choices for the left boundary where (A[i]) is the maximum (no greater element to the left).
            //((nGR[i] - i)) is the number of choices for the right boundary (no greater element to the right).
            //Multiplying these gives the total number of subarrays where (A[i]) is the maximum.
            long max_contrib = (long) (i - nGL[i]) * (nGR[i] - i);
            sum_of_max = (sum_of_max + max_contrib * A[i]) % mod;
            //((i - nSL[i])) is the number of choices for the left boundary where (A[i]) is the minimum (no smaller element to the left).
            //((nSR[i] - i)) is the number of choices for the right boundary (no smaller element to the right).
            //Multiplying these gives the total number of subarrays where (A[i]) is the minimum.
            long min_contrib = (long) (i - nSL[i]) * (nSR[i] - i);
            sum_of_min = (sum_of_min + min_contrib * A[i]) % mod;

        }
        return (int) ((sum_of_max - sum_of_min + mod) % mod);
    }

    //Populating with the size of the list (i.e., n) for "nearest right" methods
    // means there is no smaller/greater element to the right. This acts as a
    // boundary index, so when calculating the width of a subarray or range,
    // you can use n as the rightmost limit. It ensures correct calculation for
    // elements that extend to the end of the array
    private static int[] getGreaterNearestRight(int[] A) {
        int n = A.length;
        int[] nGR = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peekFirst()] <= A[i]) {
                stack.pollFirst();
            }
            nGR[i] = stack.isEmpty() ? n : stack.peekFirst();
            stack.addFirst(i);
        }
        return nGR;
    }

    private static int[] getGreaterNearestLeft(int[] A) {
        int n = A.length;
        int[] nGL = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[stack.peekFirst()] <= A[i]) {
                stack.pollFirst();
            }
            nGL[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(i);
        }
        return nGL;
    }

    private static int[] getSmallerNearestRight(int[] A) {
        int n = A.length;
        int[] nSR = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peekFirst()] >= A[i]) {
                stack.pollFirst();
            }
            nSR[i] = stack.isEmpty() ? n : stack.peekFirst();
            stack.addFirst(i);
        }
        return nSR;
    }

    private static int[] getSmallerNearestLeft(int[] A) {
        int n = A.length;
        int[] nSL = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[stack.peekFirst()] >= A[i]) {
                stack.pollFirst();
            }
            nSL[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(i);
        }
        return nSL;
    }

    private static int[] nearestSmallerLeftIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= stack.peekFirst()) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(item);
        }
        return ans;
    }

    private static int[] nearestSmallerRightIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= stack.peekFirst()) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(item);
        }
        return ans;
    }

    private static int[] nearestGreaterLeftIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int item = A.get(i);
            while (!stack.isEmpty() && item >= stack.peekFirst()) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(item);
        }
        return ans;
    }

    private static int[] nearestGreaterRightIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item >= stack.peekFirst()) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(item);
        }
        return ans;
    }

}
