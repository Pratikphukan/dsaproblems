package com.dsaproblems.DSAProblems.intermediate;

public class WhoIsWinner {

    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        int result = function(n, k);
        System.out.println(result);
    }

    private static int function(int n, int k) {
        return helper(n, k) + 1;
    }

    private static int helper(int n, int k) {
        if (n == 1)
            return 0;
        int x = (helper(n - 1, k) + k) % n;
        return x;
    }
}
