package com.dsaproblems.DSAProblems.graph00;

import java.util.ArrayList;

public class FloydWarshallAlgorithm {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> input1 = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(0);
        row1.add(50);
        row1.add(39);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(-1);
        row2.add(0);
        row2.add(1);
        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(-1);
        row3.add(10);
        row3.add(0);
        input1.add(row1);
        input1.add(row2);
        input1.add(row3);
        System.out.println("Test Case 1 Output: " + solve(input1));
        // Expected: [ [0, 49, 39], [-1, 0, -1], [-1, 10, 0] ]
    }

    private static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        // Get the number of vertices (rows in A)
        int n = A.size();
        // Define a constant for infinity (a value larger than any possible path sum)
        // Using 1000000000 is sufficient as edge weights are at most 1000000 and n<=200.
        final int INF = 1000000000;

        // Create a 2D array to store the shortest path distance
        int[][] dp = new int[n][n];

        // Initialize the dp matrix with the provided weights
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Get the weight from input matrix A for edge from i to j
                int weight = A.get(i).get(j);
                // If i equals j, the distance is 0 by definition
                if (i == j) {
                    dp[i][j] = 0;
                }
                // If there is no edge (weight == -1) and not the same vertex, assign INF to represent no connection
                else if (weight == -1) {
                    dp[i][j] = INF;
                }
                // Otherwise, use the given weight
                else {
                    dp[i][j] = weight;
                }
            }
        }

        // Apply the Floyd Warshall algorithm to update the shortest paths
        // The three nested loops represent: k -> intermediate vertex, i -> source, j -> destination.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // If vertex k is reachable from i and j is reachable from k,
                    // then we can try to improve the i->j path using k as an intermediate vertex.
                    if (dp[i][k] != INF && dp[k][j] != INF) {
                        // Update i->j distance with the minimum of its current value and the path via k
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        // Prepare the result matrix as an ArrayList of ArrayList<Integer>
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // Create a new list to store the distances for the i-th vertex
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // If the distance is equal to or exceeds INF, then no valid path exists, so add -1
                if (dp[i][j] >= INF) {
                    row.add(-1);
                } else {
                    // Otherwise, add the computed shortest path distance
                    row.add(dp[i][j]);
                }
            }
            // Add the computed row to the result matrix
            result.add(row);
        }

        // Return the final result matrix
        return result;
    }
}
