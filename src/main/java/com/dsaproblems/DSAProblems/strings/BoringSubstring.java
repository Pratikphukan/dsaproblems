package com.dsaproblems.DSAProblems.strings;

import java.util.Arrays;

public class BoringSubstring {

    public static void main(String[] args) {
        //aab
        //abcd
        String A = "aab";
        System.out.println(rearrangeCheckv1(A));
        System.out.println(rearrangeCheckv2(A));
    }

    private static int rearrangeCheckv2(String A) {
        StringBuilder odd = new StringBuilder();
        StringBuilder even = new StringBuilder();
        // odd and even stores odd and even characters respective
        for (int c : A.toCharArray()) {
            if (c % 2 == 0)
                odd.append((char) c);
            else
                even.append((char) c);
        }
        char[] oddArr = odd.toString().toCharArray();
        char[] evenArr = even.toString().toCharArray();
        Arrays.sort(oddArr);
        Arrays.sort(evenArr);
        String oddStr = new String(oddArr);
        String evenStr = new String(evenArr);
        // check if either (odd + even) or (even + odd) has no boring substrings
        return check(oddStr + evenStr) || check(evenStr + oddStr) ? 1 : 0;
    }

    public static boolean check(String s) {
        boolean ok = true;
        for (int i = 0; i + 1 < s.length(); ++i)
            ok &= (Math.abs(s.charAt(i) - s.charAt(i + 1)) != 1);
        return ok;
    }

    //working code
    //It runs in O(n) time, where n is the length of the string, and uses constant space.
    private static int rearrangeCheckv1(String A) {
        int oddMax = 'a', evenMax = 'b';
        int oddMin = 'y', evenMin = 'z';
        for (int c : A.toCharArray()) {
            if (c % 2 == 0) {
                evenMin = Math.min(evenMin, c);
                evenMax = Math.max(evenMax, c);
            } else {
                oddMin = Math.min(oddMin, c);
                oddMax = Math.max(oddMax, c);
            }
        }
        return Math.abs(oddMin - evenMax) > 1 ||
                Math.abs(oddMax - evenMin) > 1 ? 1 : 0;
    }
}
