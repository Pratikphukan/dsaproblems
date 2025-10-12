package com.dsaproblems.DSAProblems.modulararithmetic;

public class ImplementPowerFunction {

    public static void main(String[] args) {
        System.out.println(pow(90752123, 47261846, 38742364));
    }

    private static int pow(int A, int B, int C) {
        int ans = 1;
        if (A == 0) {
            return A;
        }
        for (int i = 1; i <= B; i++) {
            if (A < 0) {
                ans = (ans % C * (A % C + C)) % C;
            } else {
                ans = (ans % C * A % C) % C;
            }
        }
        return ans;
    }

}
