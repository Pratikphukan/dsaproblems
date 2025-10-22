package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RussianDollEnvelopes {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> envelopes1 = new ArrayList<>();
        envelopes1.add(createEnvelope(2, 3)); // smallest envelope
        envelopes1.add(createEnvelope(5, 4));
        envelopes1.add(createEnvelope(6, 7)); // largest envelope
        // Expected output: 3 envelopes can nest

//        envelopes3.add(createEnvelope(1, 2));
//        envelopes3.add(createEnvelope(2, 3));
//        envelopes3.add(createEnvelope(3, 4));
//        envelopes3.add(createEnvelope(4, 5));
        System.out.println("Test Case 1: Expected 3, Got: " + solve(envelopes1));
    }

    private static int solve(ArrayList<ArrayList<Integer>> A) {
        // Handle the edge case if input is null or empty
        if (A == null || A.size() == 0) {
            return 0;
        }

        // Step 1: Sort the envelopes using a custom comparator.
        // If the heights are equal, sort by widths in descending order.
        Collections.sort(A, new Comparator<ArrayList<Integer>>() {
            public int compare(ArrayList<Integer> e1, ArrayList<Integer> e2) {
                // Compare heights (first element)
                if (!e1.get(0).equals(e2.get(0))) {
                    return e1.get(0) - e2.get(0); // Ascending order for height
                } else {
                    // For equal heights, sort by width in descending order
                    return e2.get(1) - e1.get(1);
                }
            }
        });
        // Time Complexity: O(n log n) due to sorting where n is number of envelopes

        // Step 2: Extract the widths to apply Longest Increasing Subsequence (LIS)
        int[] widths = new int[A.size()];
        for (int i = 0; i < A.size(); i++) {
            // Get the width (second element) of each envelope.
            widths[i] = A.get(i).get(1);
        }

        // Step 3: Apply binary search based LIS algorithm on the widths array.
        // dp will store the smallest possible tail value for an increasing subsequence of length idx+1.
        int[] dp = new int[A.size()];
        int len = 0;  // length of the longest increasing subsequence

        // Iterate over each width
        for (int width : widths) {
            // Binary search for the correct position to place the current width in dp array
            int left = 0;
            int right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // If dp[mid] is smaller than width, we search on the right half
                if (dp[mid] < width) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // Update dp array with the new width at the found index
            dp[left] = width;
            // If the width extends the largest subsequence, increase len
            if (left == len) {
                len++;
            }
        }
        // Time Complexity: O(n log n) overall for the loop with binary search

        // Return the length of the longest increasing subsequence which equals
        // the maximum number of envelopes that can be nested.
        return len;
    }

    private static ArrayList<Integer> createEnvelope(int height, int width) {
        ArrayList<Integer> envelope = new ArrayList<>();
        envelope.add(height); // Height is the first element.
        envelope.add(width);  // Width is the second element.
        return envelope;
    }
}
