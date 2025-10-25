package com.dsaproblems.DSAProblems.toughDSA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Node {
    // children array for transitions using 4 letters: 'a', 'b', 'c', 'd'
    int[] children;
    // Failure link for the Aho-Corasick algorithm
    int fail;
    // Flag to indicate if this node (state) is terminal (forbidden)
    // because it represents the end of a restricted string or hits one via fail links.
    boolean terminal;

    // Constructor: initialize children with -1 (no child) and set terminal to false
    Node() {
        children = new int[4];  // since we have 4 letters only
        // Initialize each child to -1 indicating no edge exists yet.
        for (int i = 0; i < 4; i++) {
            children[i] = -1;
        }
        fail = 0;          // default fail link is 0 (root)
        terminal = false;  // initially not terminal
    }
}

public class GoodStrings {

    static ArrayList<Node> nodes;

    static final long MOD = 1000000007L;

    public static void main(String[] args) {
        ArrayList<String> case1 = new ArrayList<>();
        //case1.add("a");
        case1.add("cd");
        //case1.add("bdc");
        case1.add("dc");
        // For A = 3, the expected answer is 26 as explained.
        System.out.println("Test Case 1 Output: " + solve(3, case1)); // Expected output: 26
    }

    public static int solve(int A, ArrayList<String> B) {
        // Initialize the nodes list and add the root node
        nodes = new ArrayList<>();
        nodes.add(new Node()); // root index 0

        // Insert every restricted pattern into the automaton
        for (String pat : B) {
            // trim spaces in case there are extra spaces in input
            pat = pat.trim();
            // Only add patterns if they are non-empty
            if (!pat.isEmpty()) {
                addPattern(pat);
            }
        }

        // Build the automaton by establishing failure links and marking terminal states
        buildAutomaton();

        // Now, we only care about states (nodes) that are not terminal (allowed states)
        // Map original node indices to the new index in our reduced graph (only allowed states)
        Map<Integer, Integer> allowedMapping = new HashMap<>();

        // Create a list to store the allowed nodes (their original indices)
        ArrayList<Integer> allowedNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).terminal) {
                allowedMapping.put(i, allowedNodes.size());
                allowedNodes.add(i);
            }
        }

        // If the starting node (root = 0) is forbidden, then there are no valid strings.
        if (!allowedMapping.containsKey(0)) {
            return 0;
        }

        int r = allowedNodes.size();  // number of allowed states
        // Create the transition matrix for allowed states.
        // matrix[i][j] = ways to go from allowed state i to allowed state j in one step.
        long[][] matrix = new long[r][r];

        // For every allowed state, populate the transitions for each letter
        for (int i = 0; i < r; i++) {
            int origState = allowedNodes.get(i);
            // For each of the 4 characters: 'a', 'b', 'c', 'd'
            for (int c = 0; c < 4; c++) {
                int nextState = nodes.get(origState).children[c];
                // Only count transitions that lead to an allowed (non-terminal) state
                if (allowedMapping.containsKey(nextState)) {
                    // update the transition count
                    matrix[i][allowedMapping.get(nextState)] =
                            (matrix[i][allowedMapping.get(nextState)] + 1) % MOD;
                }
            }
        }

        // Perform matrix exponentiation to calculate matrix^A
        long[][] resultMatrix = matrixPower(matrix, A, r);

        // Our initial state vector is one at the index corresponding to the root (state 0).
        int startIndex = allowedMapping.get(0);
        long answer = 0;

        // Sum over all allowed states for valid sequences of length A
        // The answer is the sum of ways to end in any allowed state starting from the root.
        for (int j = 0; j < r; j++) {
            answer = (answer + resultMatrix[startIndex][j]) % MOD;
        }

        // Return the answer as an integer modulo 10^9+7
        return (int) answer;
    }

    private static void addPattern(String pat) {
        int current = 0;       // start from root node which is index 0
        // iterate through every character in the restricted string
        for (char ch : pat.toCharArray()) {
            int index = ch - 'a';  // convert character to index (0 for 'a', 1 for 'b', etc.)
            // if there is no child for this letter, create a new node
            if (nodes.get(current).children[index] == -1) {
                nodes.get(current).children[index] = nodes.size();
                nodes.add(new Node());  // add new node to our list
            }
            // move to the next node
            current = nodes.get(current).children[index];
        }
        // mark the node at the end of the pattern as terminal (forbidden)
        nodes.get(current).terminal = true;
    }

    private static void buildAutomaton() {
        // Create a queue for Breadth First Search to build failure links
        LinkedList<Integer> queue = new LinkedList<>();
        // Start by setting failure links for immediate children of the root node
        for (int c = 0; c < 4; c++) {
            int child = nodes.get(0).children[c];
            // if a child exists for the letter 'a' + c, set its failure link to 0
            if (child != -1) {
                nodes.get(child).fail = 0;
                queue.add(child);  // add child to the queue for BFS
            } else {
                // If root does not have the child, set the transition to root (fallback)
                nodes.get(0).children[c] = 0;
            }
        }

        // Process the queue until empty to set failure links for deeper nodes
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // If the current node's failure link is terminal, then the current node must also be terminal
            if (nodes.get(nodes.get(current).fail).terminal) {
                nodes.get(current).terminal = true;
            }
            // Process all transitions (letters)
            for (int c = 0; c < 4; c++) {
                int child = nodes.get(current).children[c];
                if (child != -1) {
                    // Set temporary failure pointer: move along the fail chain from current state's fail link
                    int failNode = nodes.get(current).fail;
                    // Follow fail links until we find a valid transition for letter c
                    while (nodes.get(failNode).children[c] == -1) {
                        failNode = nodes.get(failNode).fail;
                    }
                    // Set the failure link for the child node
                    nodes.get(child).fail = nodes.get(failNode).children[c];
                    // Add the child node to the queue to process its children later
                    queue.add(child);
                } else {
                    // If there is no edge from current for letter c, follow the failure link's edge
                    nodes.get(current).children[c] = nodes.get(nodes.get(current).fail).children[c];
                }
            }
        }
    }

    private static long[][] matrixPower(long[][] matrix, int power, int r) {
        // Initialize the result as an identity matrix of dimension r
        long[][] result = new long[r][r];
        for (int i = 0; i < r; i++) {
            result[i][i] = 1;
        }

        // Copy the base matrix
        long[][] base = matrix;

        // Exponentiate matrix using binary exponentiation
        while (power > 0) {
            // If the current power is odd, multiply the result with base matrix
            if ((power & 1) == 1) {
                result = multiplyMatrices(result, base, r);
            }
            // Square the base matrix for the next bit of the exponent
            base = multiplyMatrices(base, base, r);
            power /= 2;  // move to next power
        }
        return result;
    }

    private static long[][] multiplyMatrices(long[][] A, long[][] B, int r) {
        long[][] C = new long[r][r];  // store result matrix
        // For each row of matrix A
        for (int i = 0; i < r; i++) {
            // For each column of matrix B
            for (int k = 0; k < r; k++) {
                // Only proceed if A[i][k] is nonzero
                if (A[i][k] != 0) {
                    // For each column of matrix B, add contribution from A[i][k] * B[k][j]
                    for (int j = 0; j < r; j++) {
                        C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                    }
                }
            }
        }
        // Return the product matrix
        return C;
    }
}
