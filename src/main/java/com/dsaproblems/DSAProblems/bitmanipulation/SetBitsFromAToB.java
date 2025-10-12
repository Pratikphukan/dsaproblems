package com.dsaproblems.DSAProblems.bitmanipulation;

public class SetBitsFromAToB {

    public static void main(String[] args) {
        int A = 12;
        int B = 13;
        System.out.println(totalOnesInRange(A, B));
    }

    private static int totalOnesInRange(int A, int B) {
        return onesUpTo(B) - onesUpTo(A - 1);
    }

    private static int onesUpTo(int n) {
        if (n < 0) return 0;
        int total = 0;
        for (int k = 0; k < 27; k++) { // 2^61 > 2^46, safe margin
            int block = 1 << (k + 1);      // size of repeating pattern
            int onesInFull = (n + 1) / block * (1 << k);
            int rem = (n + 1) % block;
            int extra = Math.max(0, rem - (1 << k));
            total += onesInFull + extra;
        }
        return total;
    }
}
