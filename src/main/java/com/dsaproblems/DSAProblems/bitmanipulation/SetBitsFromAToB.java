package com.dsaproblems.DSAProblems.bitmanipulation;

public class SetBitsFromAToB {

    public static void main(String[] args) {
        int A = 12;
        int B = 13;
        System.out.println(totalOnesInRange(A, B));
        System.out.println(totalOnesInRangev1(A, B));
    }

    private static int totalOnesInRangev1(int A, int B) {
        return countSetBits(B) - countSetBits(A - 1);
    }

    private static int countSetBits(int n) {
        if (n == 0) return 0;
        //get the (highest power of 2) <= n
        int x = highestPowerOf2(n);
        // get 2^x using shifting left
        int powerOf2 = 1 << x;
        int bitsTill2Power = x * (powerOf2 >> 1);
        int msbSetCount = n - powerOf2 + 1;
        int rest = n - powerOf2;
        int remainingBits = countSetBits(rest);
        return bitsTill2Power + msbSetCount + remainingBits;
    }

    private static int highestPowerOf2(int n) {
        int x = 0;
        while ((1 << (x + 1)) <= n) {
            x++;
        }
        return x;
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
