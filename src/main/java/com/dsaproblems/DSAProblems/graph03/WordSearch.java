package com.dsaproblems.DSAProblems.graph03;

import java.util.ArrayDeque;
import java.util.Deque;

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";

//        char[][] board = {{'a', 'a'}};
//        String word = "aa";

        System.out.println(checkWordInGridv1(board, word));
        System.out.println(checkWordInGridv2(board, word));
        System.out.println(checkWordInGridv3(board, word));
    }

    //working code
    private static boolean checkWordInGridv3(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If the cell's value matches the first character of the word,
                // start DFS from that cell.
                if (board[row][col] == word.charAt(0)) {
                    // If DFS finds a valid path starting from cell (row, col), return true.
                    if (dfs(board, word, row, col, 0))
                        return true;
                }
            }
        }
        // If no path was found for any cell, return false.
        return false;
    }

    //working code
    private static boolean dfs(char[][] board, String word, int row, int col, int idx) {
        if (idx == word.length()) {
            return true;
        }

        // Check boundaries and verify if the current cell value matches word.charAt(index)
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(idx)) {
            // If out of bounds or character different, return false.
            return false;
        }

        // Temporarily mark the cell as visited by storing its original value.
        char temp = board[row][col];
        // Mark the current cell as visited by setting it to a special placeholder character.
        board[row][col] = '#';

        // Explore all four adjacent directions: Down, Up, Right, Left.
        boolean found = dfs(board, word, row + 1, col, idx + 1) ||
                dfs(board, word, row - 1, col, idx + 1) ||
                dfs(board, word, row, col + 1, idx + 1) ||
                dfs(board, word, row, col - 1, idx + 1);

        // Backtrack: Restore the cell's original value after exploring all possibilities.
        board[row][col] = temp;

        // Return true if the word is found from this path, otherwise false.
        return found;
    }

    private static boolean checkWordInGridv2(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0) && dfs(board, word, r, c, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int idx, boolean[][] visited) {
        if (idx == word.length()) return true;
        int rows = board.length, cols = board[0].length;
        if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
        if (visited[r][c] || board[r][c] != word.charAt(idx)) return false;

        visited[r][c] = true;
        // explore 4 directions: up, left, right, down
        boolean found = dfs(board, word, r - 1, c, idx + 1, visited)
                || dfs(board, word, r, c - 1, idx + 1, visited)
                || dfs(board, word, r, c + 1, idx + 1, visited)
                || dfs(board, word, r + 1, c, idx + 1, visited);
        visited[r][c] = false;
        return found;
    }

    private static boolean checkWordInGridv1(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    stack.addFirst(new int[]{i, j, 0});
                }
            }
        }
        while (!stack.isEmpty()) {
            int[] curr = stack.pollFirst();
            int x = curr[0], y = curr[1], idx = curr[2];
            visited[x][y] = true;
            if (idx == word.length() - 1) return true;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        board[nx][ny] == word.charAt(idx + 1) && !visited[nx][ny]) {
                    //visited[nx][ny] = true;
                    stack.addFirst(new int[]{nx, ny, idx + 1});
                }
            }
        }
        return false;
    }
}
