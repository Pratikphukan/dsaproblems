package com.dsaproblems.DSAProblems.intermediate;

public class FindSetBitsOfPositiveInteger {

    public static void main(String[] args) {
        System.out.println(countSetBits(-29));
        // 29->00.....11101->1+4+8+16, -29->1111 1111 1111 1111 1111 1111 1110 0011
        System.out.println(-29 >> 1); // -15->1111 1111 1111 1111 1111 1111 1111 0001
        System.out.println(countSetBitsOfPositiveInteger(29));
        System.out.println(countSetBitsOfPositiveIntegerv1(29));
    }

    private static int countSetBitsOfPositiveIntegerv1(int n) {
        return Integer.bitCount(n);
    }

    // if n is positive, we just need to iterate till 30 as the 31st bit will be 0
    // if n is negative, we just need to iterate till 30 as the 31st bit will be 1
    private static int countSetBits(int n) {
        int count = 0;
        if (n < 0) {
            count = 1;
        }
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    private static int countSetBitsOfPositiveInteger(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

}
