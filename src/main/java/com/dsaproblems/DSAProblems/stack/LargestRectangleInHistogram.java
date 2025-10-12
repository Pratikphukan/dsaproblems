package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] A = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(A));
    }

    public static int largestRectangleArea(int[] A) {

        // Stack to store indices of bars
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = A.length;

        // Traverse all bars
        for (int i = 0; i <= n; i++) {

            // For last iteration, use height 0 to flush the stack
            int currHeight = i == n ? 0 : A[i];

            // Pop while the current bar is shorter than the bar at stack top
            while (!stack.isEmpty() && currHeight < A[stack.peekFirst()]) {
                int height = A[stack.pollFirst()];

                // If the stack is empty, width is i (from 0 to i-1)
                // Else width is between current i and stack.peek()
                int width = stack.isEmpty() ? i : i - stack.peekFirst() - 1;
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }

            stack.addFirst(i);
        }
        return maxArea;
    }
}
