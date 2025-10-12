package com.dsaproblems.DSAProblems.backtracking;

import java.util.*;

public class NthNumberUsing12 {

    public static void main(String[] args) {
        System.out.println(findNthNumberUsing12v1(16));
        System.out.println(findNthNumberUsing12v2(16));
        System.out.println(findNthNumberUsing12v3(16));
        System.out.println(findNthNumberUsing12v4(16));
    }

    private static List<Integer> findNthNumberUsing12v4(int A) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        queue.addLast(2);
        while (ans.size() < A) {
            int multiplier = queue.removeFirst();
            ans.add(multiplier);
            queue.addLast(multiplier * 10 + 1);
            queue.addLast(multiplier * 10 + 2);
        }
        return ans;
    }

    // working solution
    private static List<Integer> findNthNumberUsing12v3(int n) {
        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2));
        int count = queue.size();
        List<Integer> ans = new ArrayList<>();
        while (count < n) {
            int multiplier = queue.pollFirst();
            ans.add(multiplier);
            queue.offerLast(multiplier * 10 + 1);
            queue.offerLast(multiplier * 10 + 2);
            count += 2;
        }
        for (int i = ans.size(); i < n; i++) {
            ans.add(queue.pollFirst());
        }
        return ans;
    }

    // working solution
    private static List<Integer> findNthNumberUsing12v2(int A) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>(Arrays.asList(1, 2));
        int count = queue.size();
        for (int i = 0; i < A; i++) {
            int multiplier = queue.removeFirst();
            ans.add(multiplier);
            if (count >= A)
                continue;
            queue.addLast(multiplier * 10 + 1);
            queue.addLast(multiplier * 10 + 2);
            count += 2;
        }
        return ans;
    }

    private static int findNthNumberUsing12v1(int n) {
        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2));
        int count = queue.size();
        int removals = 0;
        while (!queue.isEmpty() && count < n) {
            int multiplier = queue.pollFirst();
            removals += 1;
            queue.offerLast(multiplier * 10 + 1);
            queue.offerLast(multiplier * 10 + 2);
            count += 2;
        }
        int ans = 1;
        for (int i = removals; i < n; i++) {
            ans = queue.pollFirst();
        }
        return ans;
    }

}
