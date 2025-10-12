package com.dsaproblems.DSAProblems.bitmanipulation;

public class FindSetBitsOfANumber {

    public static void main(String[] args) {
        System.out.println(findSetBitsOfANumberv1(-11));
        System.out.println(findSetBitsOfANumberv2(-11));
    }

    //working code
    private static int findSetBitsOfANumberv2(int n) {
        int count = 0;
        if (n < 0) {
            count = 1; // Count the sign bit for negative numbers
        }
        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) > 0) {
                count += 1;
            }
        }
        return count;
    }

    //working code
    private static int findSetBitsOfANumberv1(int n) {
        int count = 0;
        if (n < 0) {
            count++;
        }
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

}
