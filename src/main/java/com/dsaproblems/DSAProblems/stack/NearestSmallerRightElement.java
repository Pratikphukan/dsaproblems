package com.dsaproblems.DSAProblems.stack;

import java.util.*;
import java.util.Stack;

public class NearestSmallerRightElement {

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 1, 4, 5);
        // (39, 27, 11, 4, 24, 32, 32, 1)
        // (4, 5, 2, 10, 8)
        //(3,4,2)
        //(1,1,4,5)
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(nearestSmallerElements(A));
        System.out.println(nearestSmallerIndices(A));
    }

    // every value is pushed once and popped once, so 2n iterations
    private static ArrayList<Integer> nearestSmallerIndices(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = A.size() - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= A.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans.add(0, -1);
            } else {
                ans.add(0, stack.peek());
            }
            stack.push(i);
        }
        System.out.println(stack);
        return ans;
    }

    private static ArrayList<Integer> nearestSmallerElements(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = A.size() - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans.add(0, -1);
            } else {
                ans.add(0, stack.peek());
            }
            stack.push(item);
        }
        System.out.println(stack);
        return ans;
    }

}
