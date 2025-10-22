package com.dsaproblems.DSAProblems.dp01;

public class PalindromePartitioningII {

    public static void main(String[] args) {

    }

    //not working
    public int minCutv1(String A) {
        // Get the length of the input string
        int n = A.length();
        // dp[i] represents the minimum cuts needed for substring A[0..i]
        int[] dp = new int[n];
        // isPalindrome[i][j] = true if substring A[i..j] is a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        // Fill the isPalindrome table
        // Outer loop to set the starting index of the substring
        for (int i = 0; i < n; i++) {
            // Inner loop to iterate from current index to the end of the string
            for (int j = i; j < n; j++) {
                // Characters at positions i and j should be equal
                // Also, either the length of the substring is less than 3
                // or the inner substring A[i+1..j-1] should already be a palindrome
                if (A.charAt(i) == A.charAt(j) && (j - i < 3 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        // Iterate through each character index to find minimum cuts for substring A[0..i]
        for (int i = 0; i < n; i++) {
            // If the substring from 0 to i is a palindrome, no cuts are needed
            if (isPalindrome[0][i]) {
                dp[i] = 0;
            } else {
                // Otherwise, initialize dp[i] to a high value (worst-case: i cuts)
                dp[i] = i;
                // Check for every possible cut position j from 0 to i-1
                // If the substring A[j+1..i] is a palindrome, update dp[i]
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        // dp[i] can be updated as minimum of current dp[i] or dp[j] + 1 cut
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        // Return the minimum cuts required for the entire string A[0..n-1]
        return dp[n - 1];
    }

    //working code
    public int minCutv2(String A) {
        // Determine the length of the input string
        int n = A.length();

        // Edge case: if the input string is empty, return 0 cuts required
        if (n == 0) return 0;

        // dp[i] will store the minimum cuts needed for the substring A[0...i]
        int[] dp = new int[n];

        // isPalindrome[i][j] indicates if the substring A[i...j] is a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        // Initialize dp with the worst-case scenario:
        // For substring A[0...i], the maximum possible cuts is i (each character is a separate palindrome)
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }

        // Outer loop for the ending index 'end' of the current substring
        for (int end = 0; end < n; end++) {
            // Inner loop for the starting index 'start' for the substring A[start...end]
            for (int start = 0; start <= end; start++) {
                // Check if characters at positions start and end are equal, and
                // either the length of substring is less than 3 (so automatically a palindrome)
                // or the inner substring A[start+1...end-1] is a palindrome
                if (A.charAt(start) == A.charAt(end) && (end - start < 2 || isPalindrome[start + 1][end - 1])) {
                    // Mark the substring A[start...end] as palindrome
                    isPalindrome[start][end] = true;

                    // If the palindrome starts at index 0, no cut is needed for A[0...end]
                    if (start == 0) {
                        dp[end] = 0;
                    } else {
                        // Update dp[end] to be the minimum between its current value and
                        // the number of cuts required for A[0...start-1] plus one cut for the current partition
                        dp[end] = Math.min(dp[end], dp[start - 1] + 1);
                    }
                }
            }
        }

        // Return the minimum number of cuts required for the entire string A[0...n-1]
        return dp[n - 1];
    }


}
