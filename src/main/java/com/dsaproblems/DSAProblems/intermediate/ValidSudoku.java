package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {

    public static void main(String[] args) {
        //["..5.....6","....14...",".........",".....92..","5....2...",".......3.","...54....","3.....42.","...27.6.."]
        List<String> a = new ArrayList<>();
        a.add("..5.....6");
        a.add("....14...");
        a.add(".........");
        a.add(".....92..");
        a.add("5....2...");
        a.add(".......3.");
        a.add("...54....");
        a.add("3.....42.");
        a.add("...27.6..");

        System.out.println(isValidSudokuv1(a));
        System.out.println(isValidSudokuv2(a));
        System.out.println(isValidSudokuv3(a));
    }

    private static int isValidSudokuv3(List<String> a) {
        if (a == null || a.size() != 9) return 0;
        for (String rowStr : a) if (rowStr == null || rowStr.length() != 9) return 0;

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int i = 0; i < 9; i++) {
            String rowStr = a.get(i);
            for (int j = 0; j < 9; j++) {
                char c = rowStr.charAt(j);
                if (c == '.') continue;
                int d = c - '0';
                if (d < 1 || d > 9) return 0;

                int mask = 1 << (d - 1);
                int boxIndex = (i / 3) * 3 + (j / 3);

                if ((rows[i] & mask) != 0 || (cols[j] & mask) != 0 || (boxes[boxIndex] & mask) != 0) {
                    return 0;
                }

                rows[i] |= mask;
                cols[j] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        return 1;
    }

    private static int isValidSudokuv2(List<String> a) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] sub = new boolean[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = a.get(i).charAt(j);
                if (c == '.') {
                    continue;
                }
                int num = c - '0' - 1;
                int k = i / 3 * 3 + j / 3;
                if (row[i][num] || col[j][num] || sub[k][num]) {
                    return 0;
                }
                row[i][num] = true;//ith row in sudoku has num
                col[j][num] = true;//jth column in sudoku has num
                sub[k][num] = true;//kth value in 3x3 matrix is num
            }
        }
        return 1;
    }

    private static int isValidSudokuv1(List<String> a) {
        if (a == null)
            return 0;
        int n = a.size();
        if (n != 9 || a.get(0).length() != 9)
            return 0;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            set.clear();
            for (int j = 0; j < 9; j++) {
                char c = a.get(i).charAt(j);
                if (c == '.')
                    continue;
                if (set.contains(c))
                    return 0;
                set.add(c);
            }
        }

        for (int i = 0; i < 9; i++) {
            set.clear();
            for (int j = 0; j < 9; j++) {
                char c = a.get(j).charAt(i);
                if (c == '.')
                    continue;
                if (set.contains(c))
                    return 0;
                set.add(c);
            }
        }

        for (int k = 0; k < 9; k++) {
            int x = k / 3;
            int y = k % 3;

            for (int i = 3 * x; i < 3 * x + 3; i++) {
                for (int j = 3 * y; j < 3 * y + 3; j++) {
                    char c = a.get(j).charAt(i);
                    if (c == '.')
                        continue;
                    if (set.contains(c))
                        return 0;
                    set.add(c);
                }
            }
        }

        return 1;
    }
}
