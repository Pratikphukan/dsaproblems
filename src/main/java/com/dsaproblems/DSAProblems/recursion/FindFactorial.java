package com.dsaproblems.DSAProblems.recursion;

public class FindFactorial {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(factorialv1(n));
    }

    private static int factorialv1(int A) {
        if (A == 1) {
            return 1;
        }
        return A * factorialv1(A - 1);
    }
}
