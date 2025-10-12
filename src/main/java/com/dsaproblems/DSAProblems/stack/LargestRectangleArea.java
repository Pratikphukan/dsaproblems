package com.dsaproblems.DSAProblems.stack;

import java.util.*;

public class LargestRectangleArea {

    public static void main(String[] args) {
        // (4,3,4,6,2), (2,1,5,6,2,3)
        //47,69,67,97,86,34,98,16,65,95,66,69,18,1,99,56,35,9,48,72,49,47,1,72,87,52,13,23,95,55,21,92,36,88,48,39,84,16,15,65,7,58,2,21,54,2,71,92,96,100,28,31,24,10,94,5,81,80,43,35,67,33,39,81,69,12,66,87,86,11,49,94,38,44,72,44,18,97,23,11,30,72,51,61,56,41,30,71,12,44,81,43,43,27
        ArrayList<Integer> A = new ArrayList<>(List.of(47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18, 1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65, 7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66, 87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43, 27));
        System.out.println(largestRectangleArea(A));
    }

    //working code, works for histogram problem
    private static int largestRectangleArea(ArrayList<Integer> A) {
        // height of the final rectagle, it has to be one of the heights from the
        // histogram
        if (A.size() == 1) return A.get(0);
        int maxarea = 0;
        int[] NSR = nearestSmallerRightIndices(A);
        int[] NSL = nearestSmallerLeftIndices(A);
        for (int i = 0; i < A.size(); i++) {
            int w = NSR[i] - NSL[i] - 1; // both included r-l+1, exclude one r-l, both excluded r-l-1
            int h = A.get(i);
            maxarea = Math.max(maxarea, w * h);
        }
        return maxarea;
    }

    private static int[] nearestSmallerLeftIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= A.get(stack.peekFirst())) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peekFirst();
            stack.addFirst(i);
        }
        return ans;
    }

    private static int[] nearestSmallerRightIndices(ArrayList<Integer> A) {
        int n = A.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= A.get(stack.peekFirst())) {
                stack.pollFirst();
            }
            ans[i] = stack.isEmpty() ? A.size() : stack.peekFirst();
            stack.addFirst(i);
        }
        return ans;
    }

}
