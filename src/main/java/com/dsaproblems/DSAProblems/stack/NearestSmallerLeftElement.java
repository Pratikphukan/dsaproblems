package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NearestSmallerLeftElement {

    public static void main(String[] args) {
        List<Integer> a = List.of(39, 27, 11, 4, 24, 32, 32, 1);
        // (39, 27, 11, 4, 24, 32, 32, 1)
        // (4, 5, 2, 10, 8)
        // (6,10,11,12,7)
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(nearestSmallerElements(A));
        System.out.println(nearestSmallerElementsv1(A));
        System.out.println(nearestSmallerIndices(A));
    }

    // for indices
    private static ArrayList<Integer> nearestSmallerIndices(ArrayList<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        int len = input.size();
        for (int i = 0; i < len; i++) {
            int item = input.get(i);
            while (!stack.isEmpty() && item <= input.get(stack.peekFirst())) {
                stack.pollFirst();
            }
            if (stack.isEmpty()) {
                ans.add(-1);
            } else {
                ans.add(stack.peekFirst());
            }
            stack.offerFirst(i);
        }
        return ans;
    }

    // working code
    private static ArrayList<Integer> nearestSmallerElementsv1(ArrayList<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int item : input) {
            while (!stack.isEmpty() && stack.peekFirst() >= item) {// equality is important
                stack.pollFirst();
            }
            if (stack.isEmpty()) {
                ans.add(-1);
            } else {
                ans.add(stack.peekFirst());
            }
            stack.offerFirst(item);
        }
        return ans;
    }

    private static ArrayList<Integer> nearestSmallerElements(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(A.get(0));
        ans.add(-1);
        for (int i = 1; i < A.size(); i++) {
            int item = A.get(i);
            if (item > stack.peek()) {
                ans.add(stack.peek());
            } else {
                while (!stack.isEmpty() && item <= stack.peek()) { // very important consider the = operator
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    ans.add(-1);
                } else {
                    ans.add(stack.peek());
                }
            }
            stack.push(item);
        }
        System.out.println(stack);
        return ans;
    }

}