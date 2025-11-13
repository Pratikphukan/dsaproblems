package com.dsaproblems.DSAProblems.leetcode;

import java.util.*;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] A = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(getMaximumOfEachWindowv1(A, k));
        System.out.println(Arrays.toString(getMaximumOfEachWindowv2(A, k)));
        System.out.println(Arrays.toString(getMaximumOfEachWindowv3(A, k)));
    }

    //working code
    private static int[] getMaximumOfEachWindowv3(int[] A, int B) {
        int len = A.length;
        if (B == 1) return A;
        int[] ans = new int[len - B + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            // remove indices out of current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - B)
                deque.removeFirst();
            // remove smaller values from the tail if current element is greater
            // because these elements cannot be maximum if current element is larger
            while (!deque.isEmpty() && A[deque.peekLast()] < A[i])
                deque.removeLast();
            deque.addLast(i);
            //record after B-1
            // record max for windows that are fully formed
            if (i >= B - 1) ans[i - B + 1] = A[deque.peekFirst()];
        }
        return ans;
    }

    //working code but throws TLE
    //Using PriorityQueue.remove(Object) is O(n), so your method is O(n * k) worst-case
    private static int[] getMaximumOfEachWindowv2(int[] A, int B) {
        int len = A.length;
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int[] ans = new int[len - B + 1];
        for (int i = 0; i < B; i++) {
            maxHeap.offer(A[i]);
        }
        ans[0] = maxHeap.peek();
        for (int i = B; i < len; i++) {
            maxHeap.remove(A[i - B]);
            maxHeap.offer(A[i]);
            ans[i - B + 1] = maxHeap.peek();
        }
        return ans;
    }

    private static int[] getMaximumOfEachWindowv1(int[] A, int B) {
        int first = Integer.MIN_VALUE, second = Integer.MAX_VALUE, len = A.length;
        int[] ans = new int[len - B + 1];
        if (B == 1) {
            return A;
        }
        for (int i = 0; i < B; i++) {
            if (A[i] >= first) {
                second = first;
                first = A[i];
            }
        }
        ans[0] = first;
        int max;
        for (int i = B; i < len; i++) {
            if (A[i - B] == first) max = second;
            if (A[i] >= first) {
                second = A[i];
                first = A[i];
            }
        }
        return ans;
    }
}
