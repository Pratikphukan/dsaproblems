package com.dsaproblems.DSAProblems.strings;

import java.math.BigInteger;

public class PowerOf2 {

    //Find if Given number is power of 2 or not.
    //More specifically, find if given number can be expressed as 2^k where k >= 1.
    public static void main(String[] args) {
        String A = "1267650600228229401496703205376";
        System.out.println(checkPowerOf2v1(A));
    }

    private static boolean checkPowerOf2v1(String A) {
        if (A.equals("1")) return false;
        BigInteger n = new BigInteger(A);
        BigInteger x = n.subtract(BigInteger.ONE);
        BigInteger y = n.and(x);
        return y.equals(BigInteger.ZERO);
    }
}
