package com.dsaproblems.DSAProblems.leetcode;

public class ReverseInteger {

    public static void main(String[] args) {
        //-1563847412
        //1534236469
        int input = -1563847412;
        System.out.println(reverseIntegerv1(input));
    }

    private static int reverseIntegerv1(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 ||
                    (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            result = result * 10 + digit;
        }
        return result;
    }
}
