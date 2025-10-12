package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixSearch {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 2, 5, 7));
        input.add(Arrays.asList(10, 11, 16, 20));
        input.add(Arrays.asList(23, 30, 34, 50));
        int B = 30;
        System.out.println(searchInMatrixV1(input, B));
        System.out.println(searchInMatrixV2(input, B));
    }

    private static int searchInMatrixV2(List<List<Integer>> A, int B) {
        int n = A.size(), m = A.get(0).size();
        // assume all elements are added to a list and after that it is sorted
        // last index will be n * m - 1
        int l = 0, h = n * m - 1, ans = -1;
        while (l <= h) {
            int mid = (h + l) / 2;
            int row = mid / m;
            int col = mid % m;
            if (A.get(row).get(col) > B)
                h = mid - 1;
            else {
                ans = mid;
                l = mid + 1;
            }
        }
        if (ans == -1 || A.get(ans / m).get(ans % m) != B)
            return 0;
        return 1;
    }

    private static int searchInMatrixV1(List<List<Integer>> input, int B) {
        int rows = input.size();
        int cols = input.get(0).size();
        int low = 0;
        int high = rows * cols - 1;
        int mid = 0;
        int row = 0;
        int col = 0;
        int ans = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            row = mid / cols;
            col = mid % cols;
            if (input.get(row).get(col) > B) {
                high = mid - 1;
            } else if (input.get(row).get(col) < B) {
                low = mid + 1;
            } else {
                ans = 1;
                break;
            }
        }
        return ans;
    }

}
