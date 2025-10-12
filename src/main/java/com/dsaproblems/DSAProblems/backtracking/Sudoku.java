package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;

public class Sudoku {

    public static void main(String[] args) {
        // Test case: Standard Sudoku example from the prompt.
        ArrayList<ArrayList<Character>> board = new ArrayList<>();

        // Create the 9x9 board row by row using the provided example
        board.add(new ArrayList<>() {{
            add('5');
            add('3');
            add('.');
            add('.');
            add('7');
            add('.');
            add('.');
            add('.');
            add('.');
        }});
        board.add(new ArrayList<>() {{
            add('6');
            add('.');
            add('.');
            add('1');
            add('9');
            add('5');
            add('.');
            add('.');
            add('.');
        }});
        board.add(new ArrayList<>() {{
            add('.');
            add('9');
            add('8');
            add('.');
            add('.');
            add('.');
            add('.');
            add('6');
            add('.');
        }});
        board.add(new ArrayList<>() {{
            add('8');
            add('.');
            add('.');
            add('.');
            add('6');
            add('.');
            add('.');
            add('.');
            add('3');
        }});
        board.add(new ArrayList<>() {{
            add('4');
            add('.');
            add('.');
            add('8');
            add('.');
            add('3');
            add('.');
            add('.');
            add('1');
        }});
        board.add(new ArrayList<>() {{
            add('7');
            add('.');
            add('.');
            add('.');
            add('2');
            add('.');
            add('.');
            add('.');
            add('6');
        }});
        board.add(new ArrayList<>() {{
            add('.');
            add('6');
            add('.');
            add('.');
            add('.');
            add('.');
            add('2');
            add('8');
            add('.');
        }});
        board.add(new ArrayList<>() {{
            add('.');
            add('.');
            add('.');
            add('4');
            add('1');
            add('9');
            add('.');
            add('.');
            add('5');
        }});
        board.add(new ArrayList<>() {{
            add('.');
            add('.');
            add('.');
            add('.');
            add('8');
            add('.');
            add('.');
            add('7');
            add('9');
        }});

        // Create an instance of the solution
        Sudoku sol = new Sudoku();
        // Call the sudoku solver which modifies the board in place
        sol.solveSudoku(board);

        // Print out the solved sudoku board
        System.out.println("Solved Sudoku Board:");
        for (ArrayList<Character> row : board) {
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    // Function to solve the sudoku puzzle by modifying the board in place.
    public void solveSudoku(ArrayList<ArrayList<Character>> board) {
        // Start the backtracking algorithm from the beginning of the board (row 0, col 0)
        solve(board);
    }

    // Recursive helper function that applies backtracking technique.
    private boolean solve(ArrayList<ArrayList<Character>> board) {
        // Iterate through each row of the board
        for (int row = 0; row < 9; row++) {
            // Iterate through each column of the board
            for (int col = 0; col < 9; col++) {
                // If the cell is empty (denoted by '.')
                if (board.get(row).get(col) == '.') {
                    // Try all possible digits from '1' to '9'
                    for (char c = '1'; c <= '9'; c++) {
                        // Check if placing the digit 'c' at position (row, col) is valid
                        if (isValid(board, row, col, c)) {
                            // Place the digit in the cell
                            board.get(row).set(col, c);
                            // Recursively attempt to fill the rest of the board
                            if (solve(board)) {
                                // If recursion returns true, puzzle is solved, propagate success upward
                                return true;
                            }
                            // Backtracking step: reset the current cell to '.' if the attempt was unsuccessful
                            board.get(row).set(col, '.');
                        }
                    }
                    // If no valid digit is found for an empty cell, return false to trigger backtracking
                    return false;
                }
            }
        }
        // If the entire board has been processed with no empty cells found, then the puzzle is solved
        return true;
    }

    // Helper function to check if placing a character 'c' at board[row][col] is valid
    private boolean isValid(ArrayList<ArrayList<Character>> board, int row, int col, char c) {
        // Check the row, column, and 3x3 subgrid constraints

        // Check for the digit 'c' in the current row and current column
        for (int i = 0; i < 9; i++) {
            // Check the row: if any cell in row 'row' has the digit 'c'
            if (board.get(row).get(i) == c) {
                return false;
            }
            // Check the column: if any cell in column 'col' has the digit 'c'
            if (board.get(i).get(col) == c) {
                return false;
            }
        }

        // Determine the starting coordinates of the 3x3 subgrid that the cell belongs to
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Loop through each cell in the 3x3 subgrid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // If any cell in the subgrid already contains the digit 'c', return false
                if (board.get(startRow + i).get(startCol + j) == c) {
                    return false;
                }
            }
        }

        // If all checks pass, it is valid to place 'c' at board[row][col]
        return true;
    }
}
