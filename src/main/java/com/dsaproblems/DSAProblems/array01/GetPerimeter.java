package com.dsaproblems.DSAProblems.array01;

public class GetPerimeter {

    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(getLandPerimeter(grid1));

        int[][] grid2 = {
                {1}
        };
        System.out.println(getLandPerimeter(grid2));
    }

    public static int getLandPerimeter(int[][] A) {
        int perimeter = 0;
        int rows = A.length;
        int cols = A[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] == 1) {
                    if (i == 0 || A[i - 1][j] == 0)
                        perimeter++; // If the cell is in the first row or the top neighbor is water, add 1 to perimeter
                    if (i == rows - 1 || A[i + 1][j] == 0)
                        perimeter++; // If the cell is in the last row or the bottom neighbor is water, add 1 to perimeter
                    if (j == 0 || A[i][j - 1] == 0)
                        perimeter++; // If the cell is in the first column or the left neighbor is water, add 1 to perimeter
                    if (j == cols - 1 || A[i][j + 1] == 0)
                        perimeter++; // If the cell is in the last column or the right neighbor is water, add 1 to perimeter
                }
            }
        }
        return perimeter;
    }
}
