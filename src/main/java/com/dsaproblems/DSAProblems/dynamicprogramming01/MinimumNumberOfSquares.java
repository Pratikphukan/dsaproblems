package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumNumberOfSquares {

    public static void main(String[] args) {
        System.out.println(findCountOfNumbersWhoseSquareSumv1(12));
        System.out.println(findCountOfNumbersWhoseSquareSumv2(12));
        System.out.println(findCountOfNumbersWhoseSquareSumv3(12));


        System.out.println(countOfNumbersWhoseSquareSumv1(6, 6));
        System.out.println(countOfNumbersWhoseSquareSumv3(6));
    }

    //working code, throws TLE
    private static int countOfNumbersWhoseSquareSumv1(int n, int sum) {
        if (n == 1) return sum;
        int count = countOfNumbersWhoseSquareSumv1(n - 1, sum);
        if (sum - n * n >= 0) {
            count = Math.min(count,
                    1 + countOfNumbersWhoseSquareSumv1(n, sum - n * n));
        }
        return count;
    }

    //working code, optimised
    //Time Complexity (TC):
    //O(n*sqrt(n)) — For each i from 2 to n, the inner loop runs up to sqrt{i} times.
    //Space Complexity (SC):
    //O(n) — The dp array stores results for all numbers up to n.
    private static int countOfNumbersWhoseSquareSumv3(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1; //dp[0] is 0, and dp[1] = 1 as there is 1 way to get 1
        for (int i = 2; i <= n; i++) {
            dp[i] = i; // base case as every number can be represented as sum of 1s
            for (int x = 1; x * x <= i; x++) {
                dp[i] = Math.min(dp[i], dp[i - x * x] + 1);
            }
        }
        return dp[n];
    }

    // working code but takes a lot of time
    private static int findCountOfNumbersWhoseSquareSumv3(int n) {
        if (n <= 3) {
            return n;
        }
        int res = n;
        for (int x = 1; x * x <= n; x++) {
            res = Math.min(res, 1 + findCountOfNumbersWhoseSquareSumv3(n - x * x));
        }
        return res;
    }

    private static Integer findCountOfNumbersWhoseSquareSumv2(int n) {
        List<Integer> dp = new ArrayList<>(Arrays.asList(0, 1));
        for (int i = 2; i <= n; i++) {
            dp.add(i);// base case as every number can be represented as sum of 1s
            for (int x = 1; x * x <= i; x++) {
                dp.set(i, Math.min(dp.get(i), dp.get(i - x * x) + 1));
            }
        }
        System.out.println(dp);
        return dp.get(n);
    }

    private static int findCountOfNumbersWhoseSquareSumv1(int input) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= input; i++) {
            squares.add(i * i);
        }
        return minCountOfSquareSums(squares, squares.size(), input);
    }

    private static int minCountOfSquareSums(List<Integer> squares, int n, int input) {
        if (input < 0 || n <= 0) {
            return 0;
        }
        if (input == 0) {
            return 1;
        }
        return Math.min(minCountOfSquareSums(squares, n - 1, input - squares.get(n - 1)) + 1,
                minCountOfSquareSums(squares, n - 1, input));
    }
}
