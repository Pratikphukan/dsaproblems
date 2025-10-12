package com.dsaproblems.DSAProblems.backtracking;

import java.util.*;

public class NDigitNumberUsing12 {

    public static void main(String[] args) {
        System.out.println(findNDigitNumbersUsing12v1(4));
        //System.out.println(findNDigitNumbersUsing12v2(10));
        int n = 4;
        int[] currList = new int[n];
        List<Integer> ans = new ArrayList<>();
        findNDigitNumbersUsing12v3(n, 0, currList, ans);
        System.out.println(ans);
    }

    // working solution,TC is O(n*2^n)
    private static void findNDigitNumbersUsing12v3(int n, int idx, int[] currList, List<Integer> ans) {
        if (idx == n) {
            int num = 0;
            int multiplier = 1;
            for (int i = n - 1; i >= 0; i--) {
                num += currList[i] * multiplier;
                multiplier *= 10;
            }
            ans.add(num);
            return;
        }
        currList[idx] = 1;
        findNDigitNumbersUsing12v3(n, idx + 1, currList, ans);
        currList[idx] = 2;
        findNDigitNumbersUsing12v3(n, idx + 1, currList, ans);
    }

    //throws OutOfMemoryError for n=10, TC is O(2^n)
    private static List<Integer> findNDigitNumbersUsing12v2(int n) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2));
        while (!queue.isEmpty()) {
            int num = queue.pollFirst();
            if (String.valueOf(num).length() == n) {
                //System.out.println(queue.size());
                ans.add(num);
            } else {
                queue.offerLast(num * 10 + 1);
                queue.offerLast(num * 10 + 2);
            }
        }
        return ans;
    }

    private static List<Integer> findNDigitNumbersUsing12v1(int n) {
        Deque<Integer> queue = new ArrayDeque<>(Arrays.asList(1, 2));
        int count = queue.size();
        int insertions = (1 << (n + 1)) - 2; // 2^1+2^2+....+2^n -> 2^(n+1)-1-2^0 insertions
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty() && count < insertions) {
            int multiplier = queue.pollFirst();
            queue.offerLast(multiplier * 10 + 1);
            queue.offerLast(multiplier * 10 + 2);
            count += 2;
        }
        while (!queue.isEmpty()) {
            ans.add(queue.pollFirst());
        }
        return ans;
    }
}
