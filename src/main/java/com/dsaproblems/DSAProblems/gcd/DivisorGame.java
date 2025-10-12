package com.dsaproblems.DSAProblems.gcd;

public class DivisorGame {

    public static void main(String[] args) {
        int A = 12;
        int B = 3;
        int C = 2;
        System.out.println(getSpecialIntsv1(A, B, C));
        System.out.println(getSpecialIntsv2(A, B, C));
        System.out.println(getSpecialIntsv3(A, B, C));
        System.out.println(getSpecialIntsv4(A, B, C));

        //52503, 9013, 2254
        //12, 3, 2
    }

    //working solution and optimal
    private static int getSpecialIntsv4(int A, int B, int C) {
        int lcm = lcm(B, C);
        if (lcm > A) return 0; //lcm is greater than A, there are no numbers <= A which are divisible by both B and C
        int x = A / lcm; // numbers are lcm, 2*lcm, 3*lcm, ..., x*lcm where x*lcm <= A
        int y = (Math.max(B, C) - 1) / lcm; // numbers are lcm, 2*lcm, 3*lcm, ..., y*lcm where y*lcm < max(B,C)
        return x - y;
    }

    //working solution and optimal
    private static int getSpecialIntsv3(int A, int B, int C) {
        int lcm = lcm(B, C);
        if (lcm > A) return 0; //lcm is greater than A, there are no numbers <= A which are divisible by both B and C
        int count = 0; // counting lcm itself
        for (int i = 1; lcm * i <= A; i++) {
            count += 1;
        }
        return count;
    }

    private static int getSpecialIntsv2(int A, int B, int C) {
        int lcm = lcm(B, C);
        if (lcm > A) return 0; //lcm is greater than A, there are no numbers <= A which are divisible by both B and C
        int count = 1; // counting lcm itself
        for (int i = lcm + 1; i <= A; i++) {
            if (i % B == 0 && i % C == 0) {
                count++;
            }
        }
        return count;
    }

    private static int lcm(int B, int C) {
        return (int) ((long) B * (long) C) / gcd(B, C);
    }

    private static int gcd(int B, int C) {
        while (C != 0) {
            int temp = C;
            C = B % C;
            B = temp;
        }
        return B;
    }

    //working but throws TLE for large inputs
    private static int getSpecialIntsv1(int A, int B, int C) {
        int max = Math.max(B, C);
        int count = 0;
        for (int i = max; i <= A; i++) {
            if (i % B == 0 && i % C == 0) {
                count++;
            }
        }
        return count;
    }
}
