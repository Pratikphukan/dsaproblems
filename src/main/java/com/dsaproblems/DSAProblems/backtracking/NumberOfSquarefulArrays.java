package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfSquarefulArrays {

    private static int count = 0;

    public static void main(String[] args) {
        ArrayList<Integer> testCase1 = new ArrayList<>();
        testCase1.add(1);
        testCase1.add(17);
        testCase1.add(8);
        System.out.println(solve(testCase1));
    }

    private static int solve(ArrayList<Integer> A) {
        // Map to count frequency of each number in A
        Map<Integer, Integer> freq = new HashMap<>();
        // Build frequency map
        for (int num : A) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Build graph mapping each number to a list of numbers that can follow it (sum is perfect square)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // For each unique number in the array
        for (int x : freq.keySet()) {
            // Initialize neighbor list for the number
            graph.put(x, new ArrayList<>());
            // For each other unique number in the array
            for (int y : freq.keySet()) {
                // If x and y form a perfect square, add y to the neighbor list of x
                if (isPerfectSquare(x, y)) {
                    graph.get(x).add(y);
                }
            }
        }

        // For each unique starting number, start DFS
        count = 0;  // Reset count before starting DFS
        int n = A.size();  // Total number of elements in the permutation
        for (int x : freq.keySet()) {
            // Use number x as the start, decrement its frequency
            freq.put(x, freq.get(x) - 1);

            // Start DFS with current element x and remaining count n - 1
            dfs(x, n - 1, freq, graph);

            // Restore the frequency of x after DFS
            freq.put(x, freq.get(x) + 1);
        }

        // Return the total count of valid squareful permutations
        return count;
    }

    private static void dfs(int curr, int remain, Map<Integer, Integer> freq, Map<Integer, List<Integer>> graph) {
        // If no elements remain, we have used all numbers and found a valid permutation
        if (remain == 0) {
            count++;  // Increase count of valid permutations
            return;
        }

        // Get the list of neighbor candidates that can follow the current element
        if (!graph.containsKey(curr)) return;

        for (Integer next : graph.get(curr)) {
            // Check if this candidate is available to use (frequency > 0)
            if (freq.get(next) > 0) {
                // Use this candidate, decrement its frequency
                freq.put(next, freq.get(next) - 1);

                // Recurse with the candidate as the new current element and decrease remaining count
                dfs(next, remain - 1, freq, graph);

                // Backtrack: restore the frequency count for the candidate
                freq.put(next, freq.get(next) + 1);
            }
        }
    }

    private static boolean isPerfectSquare(int a, int b) {
        int sum = a + b;
        // Compute the square root of sum
        int sqrt = (int) Math.sqrt(sum);
        // Check if the square of sqrt equals the sum
        return sqrt * sqrt == sum;
    }
}
