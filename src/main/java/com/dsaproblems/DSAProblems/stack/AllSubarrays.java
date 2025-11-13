package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class AllSubarrays {

    public static void main(String[] args) {
        ArrayList<Integer> test1 = new ArrayList<>();
        test1.add(2);
        test1.add(3);
        test1.add(1);
        test1.add(4);
        System.out.println(getMaxXorAmongSubarrayv1(test1));
    }

    private static int getMaxXorAmongSubarrayv1(ArrayList<Integer> A) {
        int maxXor = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : A) {
            // While the stack is not empty and the top element is less than current element.
            while (!stack.isEmpty() && stack.peekFirst() < num) {
                int top = stack.pollFirst();
                int currXor = top ^ num;// Calculate XOR of the popped element and current element
                maxXor = Math.max(maxXor, currXor);
            }
            // If the stack is not empty after popping smaller elements,
            // then the current number and the element at the top are candidates.
            if (!stack.isEmpty()) {
                int currXor = stack.peekFirst() ^ num;
                maxXor = Math.max(maxXor, currXor);
            }
            stack.addFirst(num);
        }
        return maxXor;
    }
}
