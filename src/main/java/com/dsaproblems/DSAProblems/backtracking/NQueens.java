package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class NQueens {

    public static void main(String[] args) {
        int A = 2;
        System.out.println(Arrays.deepToString(solveNQueensv1(A)));
    }

    private static String[][] solveNQueensv1(int A) {
        String[][] sol = new String[A][A];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                sol[i][j] = ".";
            }
        }
        queenProblemUtil(0, sol);
        return sol;
    }

    private static boolean queenProblemUtil(int col, String[][] sol) {
        int N = sol.length;
        if (col >= N) {
            return true;
        }
        for (int row = 0; row < N; row++) {
            if (isSafeToPlaceQueen(row, col, sol)) {
                sol[row][col] = "Q";
                if (queenProblemUtil(col + 1, sol)) {
                    return true;
                }
                sol[row][col] = ".";
            }
        }
        return false;
    }

    private static boolean isSafeToPlaceQueen(int row, int col, String[][] sol) {
        int i, j, N = sol.length;
        for (i = 0; i < col; i++) {
            if (sol[row][i] == "Q") {
                return false;
            }
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (sol[i][j] == "Q") {
                return false;
            }
        }
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (sol[i][j] == "Q") {
                return false;
            }
        }
        return true;
    }

    //working code
    public ArrayList<ArrayList<String>> solveNQueensv2(int n) {
        ArrayList<ArrayList<String>> solutions = new ArrayList<>();
        // Array to store the column index of the queen for each row
        int[] queens = new int[n];
        // Initialize all placements to -1 (meaning no queen placed)
        for (int i = 0; i < n; i++) {
            queens[i] = -1;
        }

        // Boolean arrays to mark if a column or a diagonal is occupied
        boolean[] cols = new boolean[n];                // columns occupied
        boolean[] diag = new boolean[2 * n - 1];          // main diagonal (i - j + n - 1)
        boolean[] antiDiag = new boolean[2 * n - 1];      // anti-diagonal (i + j)

        // Start backtracking from row 0
        backtrack(0, n, queens, cols, diag, antiDiag, solutions);

        // Sort solutions in reverse lexicographical order based on board representation
        Collections.sort(solutions, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> board1, ArrayList<String> board2) {
                // Compare row by row
                for (int i = 0; i < board1.size(); i++) {
                    // Compare the two strings in each row
                    int cmp = board1.get(i).compareTo(board2.get(i));
                    // If rows are not equal, reverse the order by negating the comparison result
                    if (cmp != 0) {
                        return -cmp;  // negative sign for reverse order
                    }
                }
                return 0; // boards are identical in lex order
            }
        });

        return solutions;
    }

    private void backtrack(int row, int n, int[] queens, boolean[] cols, boolean[] diag, boolean[] antiDiag, ArrayList<ArrayList<String>> solutions) {
        // Base case: if row equals n, a valid configuration is found
        if (row == n) {
            // Convert the queens position to board representation and add to solutions
            solutions.add(generateBoard(queens, n));
            return;
        }

        // Try placing a queen in every column for the current row
        for (int col = 0; col < n; col++) {
            // Calculate indices for the diagonals
            int d = row - col + n - 1; // main diagonal index
            int ad = row + col;        // anti-diagonal index

            // If the column, diagonal or anti-diagonal is already occupied, skip this column
            if (cols[col] || diag[d] || antiDiag[ad]) {
                continue;
            }

            // Place queen at the current row and column
            queens[row] = col;
            cols[col] = true;
            diag[d] = true;
            antiDiag[ad] = true;

            // Recursively try placing queen for the next row
            backtrack(row + 1, n, queens, cols, diag, antiDiag, solutions);

            // Backtracking: remove queen and reset the states for column and diagonals
            queens[row] = -1;
            cols[col] = false;
            diag[d] = false;
            antiDiag[ad] = false;
        }
    }

    private ArrayList<String> generateBoard(int[] queens, int n) {
        ArrayList<String> board = new ArrayList<>();
        // For every row, create a string with 'Q' at queen's column and '.' elsewhere
        for (int i = 0; i < n; i++) {
            // StringBuilder for the row string
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // If column matches the queen index, append 'Q'
                if (j == queens[i]) {
                    row.append('Q');
                } else {
                    // Else, append '.'
                    row.append('.');
                }
            }
            // Add the row string to the board configuration
            board.add(row.toString());
        }
        return board;
    }
}
