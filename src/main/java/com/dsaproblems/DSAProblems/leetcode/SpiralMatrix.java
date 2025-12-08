package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12},
                {13, 14, 15}};
        System.out.println(getSpiralOrderv1(matrix));
        System.out.println(getSpiralOrderv2(matrix));
    }

    //working code
    private static List<Integer> getSpiralOrderv2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        int total = rows * cols;
        while (result.size() < total) {
            for (int j = left; j <= right && result.size() < total; j++) result.add(matrix[top][j]);
            top++;
            for (int i = top; i <= bottom && result.size() < total; i++) result.add(matrix[i][right]);
            right--;
            for (int j = right; j >= left && result.size() < total; j--) result.add(matrix[bottom][j]);
            bottom--;
            for (int i = bottom; i >= top && result.size() < total; i--) result.add(matrix[i][left]);
            left++;
        }
        return result;
    }

    //working code
    private static List<Integer> getSpiralOrderv1(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) result.add(matrix[top][j]);
            top++;
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) result.add(matrix[bottom][j]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
                left++;
            }
        }
        return result;
    }
}
