package com.dsaproblems.DSAProblems.subarrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class MinSubarrayLen {

    public static void main(String[] args) {
        //2,3,1,2,4,3|7
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 1, 2, 4, 3)); //All numbers are non-negative
        int targetSum = 7;

        System.out.println(subarraySumGreaterEqualK(input, targetSum));

        System.out.println(shortestSubarrayAtLeastK(input, targetSum));
    }

    private static int shortestSubarrayAtLeastK(ArrayList<Integer> A, int targetSum) {
        int n = A.size();
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + A.get(i);

        Deque<Integer> dq = new ArrayDeque<>();
        int ans = n + 1;

        for (int i = 0; i <= n; i++) {
            // Try to pop from front while we already have sum >= k
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= targetSum) {
                ans = Math.min(ans, i - dq.pollFirst());
            }
            // Maintain increasing prefix sums in deque
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return ans == n + 1 ? -1 : ans;
    }

    //with non-negative nums, expanding the window only increases the sum; once it meets/exceeds target,
    // shrink from the left to find the shortest window ending at right.
    private static int subarraySumGreaterEqualK(ArrayList<Integer> A, int targetSum) {
        int left = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (int right = 0; right < A.size(); right++) {
            sum += A.get(right);
            while (sum >= targetSum) {             // shrink from the left
                ans = Math.min(ans, right - left + 1);
                sum -= A.get(left);
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
