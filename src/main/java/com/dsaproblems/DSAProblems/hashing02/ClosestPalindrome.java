package com.dsaproblems.DSAProblems.hashing02;

public class ClosestPalindrome {

    public static void main(String[] args) {
        //String A = "abba";
        //String A = "aba";
        //'abcca'
        String A = "aaaaaaaaaabaaaaaaaaa";
        System.out.println(closestPalindromev1(A));
        System.out.println(closestPalindromev2(A));
    }

    //working code
    private static String closestPalindromev2(String A) {
        int count = 0;
        int n = A.length();
        for (int i = 0; i <= n / 2; i++) {
            if (i == (n - 1 - i) && count == 0) {
                count += 1;//odd length->n/2->only mid, even length->n/2->first mid
            }
            if (i < (n - 1 - i) && A.charAt(i) != A.charAt(n - 1 - i)) {
                count++;
                if (count > 1) {
                    return "NO"; // More than one mismatch, cannot be a palindrome with one change
                }
            }
        }
        return count == 1 ? "YES" : "NO"; // One mismatch, can be changed to make it a palindrome
    }

    //working code
    private static String closestPalindromev1(String A) {
        int left = 0;
        int right = A.length() - 1;
        int count = 0;
        while (left <= right) {
            if (left == right && count == 0) {
                // If we are at the middle character of an odd-length string
                // and no mismatch has been found yet, we can change it to any character
                return "YES";
            }
            if (A.charAt(left) != A.charAt(right)) {
                count++;
                if (count > 1) {
                    return "NO"; // More than one mismatch, cannot be a palindrome with one change
                }
            }
            left++;
            right--;
        }
        if (count == 1) {
            return "YES"; // One mismatch, can be changed to make it a palindrome
        }
        return "NO"; // Already a palindrome
    }
}
