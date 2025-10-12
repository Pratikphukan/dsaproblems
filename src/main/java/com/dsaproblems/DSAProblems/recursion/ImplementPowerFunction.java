package com.dsaproblems.DSAProblems.recursion;

public class ImplementPowerFunction {

    //working code
    public long powv1(int A, int B, int C) {
        if (A == 0) {
            return 0;
        }
        if (B == 0) {
            return 1;
        }
        long y = powv1(A, B / 2, C);
        if (B % 2 == 0) {
            return (y * y) % C;
        }
        if (A < 0) {
            return (((y * y) % C) * A) % C + C;
        }
        return ((y * y) % C * A) % C;
    }

    //working code
    public int pow(int A, int B, int C) {
        if (A == 0)
            return 0;
        if (B == 0)
            return 1;
        long ans = pow(A, B / 2, C);// The idea is to compute power in halves, and then combine.
        ans = (ans * ans) % C; //This squares the result of the half-power, and applies the modulo to prevent overflow.
        if (B % 2 == 1) //If B is odd,
            ans = (ans * A); //multiply one more A
        ans = (ans + C) % C; //This handles negative modulo results.
        //Adding C ensures the result is non-negative and then % C ensures it's within bounds
        return (int) ans;
    }
}
