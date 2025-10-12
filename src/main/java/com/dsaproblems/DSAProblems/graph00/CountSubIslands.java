package com.dsaproblems.DSAProblems.graph00;

import java.util.*;

public class CountSubIslands {

    /*
    A = [ [1,0,1,0,1],
      [1,1,1,1,1],
      [0,0,0,0,0],
      [1,1,1,1,1],
      [1,0,1,0,1] ],


    B = [ [0,0,0,0,0],
      [1,1,1,1,1],
      [0,1,0,1,0],
      [0,1,0,1,0],
      [1,0,0,0,1] ]
     */

    /*
    You are given two binary matrices A and B, each of size m x n. The matrices consist of only 0's and 1's, where:
    0 represents water
    1 represents land
    An island is formed by a group of 1's connected 4-directionally (up, down, left, or right).
    Cells outside the matrix boundaries are treated as water.

    Now, consider an island in matrix B. It is called a sub-island if every land cell of that
    island is also part of the same island in matrix A (i.e., the corresponding cell in A is also
    land, and all cells of the island in B fall within an island in A).

    Your task is to return the number of sub-islands in matrix B.
     */
    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(1, 0, 1, 0, 1)));
        A.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        A.add(new ArrayList<>(Arrays.asList(1, 0, 1, 0, 1)));
        List<List<Integer>> B = new ArrayList<>();
        B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)));
        B.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0)));
        B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0)));
        B.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0, 1)));
        System.out.println(findAllSubIslandsv1(A, B));
    }

    private static int findAllSubIslandsv1(List<List<Integer>> A, List<List<Integer>> B) {
        int rows = B.size();
        int cols = B.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int connectedIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (B.get(i).get(j) == 1 && A.get(i).get(j) == 1 && !visited[i][j]) {
                    //dfs(input, visited, i, j);
                    if (bfs(A, B, visited, i, j))
                        connectedIslands++;
                }
            }
        }
        return connectedIslands;
    }

    private static boolean bfs(List<List<Integer>> A, List<List<Integer>> B, boolean[][] visited, int i, int j) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int rows = B.size();
        int cols = B.get(0).size();
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{
                i, j
        });
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.removeFirst();
            int x = cell[0], y = cell[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 &&
                        nx < rows &&
                        ny >= 0 &&
                        ny < cols &&
                        B.get(nx).get(ny) == 1 &&
                        !visited[nx][ny]) {
                    if (A.get(nx).get(ny) == 0) {
                        return false;
                    }
                    visited[nx][ny] = true;
                    queue.addLast(new int[]{
                            nx, ny
                    });
                }
            }
        }
        return true;
    }
}
