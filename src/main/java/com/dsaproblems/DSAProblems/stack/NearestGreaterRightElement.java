package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NearestGreaterRightElement {

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 1, 4, 5);
        // (39, 27, 11, 4, 24, 32, 32, 1)
        // (4, 5, 2, 10, 8)
        //(3,4,2)
        //(1,1,4,5)
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(nearestGreaterIndicesv1(A));
        System.out.println(nearestGreaterIndicesv2(A));
    }

    private static ArrayList<Integer> nearestGreaterIndicesv2(ArrayList<Integer> A) {
        int n = A.size();
        ArrayList<Integer> ans = new ArrayList<>(java.util.Collections.nCopies(n, -1));
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A.get(i) >= A.get(stack.peek())) { //poll all elements to the immediate right till A(i)<A(peek of stack)
                stack.pollFirst();
            }
            if (!stack.isEmpty()) {
                ans.set(i, stack.peek() + 1);
            }
            stack.addFirst(i);
        }
        return ans;
    }

    private static ArrayList<Integer> nearestGreaterIndicesv1(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = A.size() - 1; i >= 0; i--) {
            int item = A.get(i);
            while (!stack.isEmpty() && item >= A.get(stack.peek())) {
                stack.pollFirst();
            }
            if (stack.isEmpty()) {
                ans.add(0, -1);
            } else {
                ans.add(0, stack.peek() + 1);//stack top is the nearest greater element index
            }
            stack.addFirst(i);
        }
        System.out.println(stack);
        return ans;
    }
}
