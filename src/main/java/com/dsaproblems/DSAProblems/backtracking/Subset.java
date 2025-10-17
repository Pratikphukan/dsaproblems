package com.dsaproblems.DSAProblems.backtracking;

import java.util.*;

public class Subset {

    public static void main(String[] args) {
        //9, -20, -11, -8, -4, 2, -12, 14, 1, -18
        //1, 2, 3
        //1,2,2
        //8, 10, 6, 11, 1, 16, 8
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(findAllSubsetsv1(input));
        System.out.println(findAllSubsetsv2(input));
        System.out.println(findAllSubsetsv3(input));
        System.out.println(findAllSubsetsv4(input));
        System.out.println(findAllSubsetsv5(input));
    }

    //Time Complexity: O(2^n*n), where n is the size of the input. This is optimal since there are (2^n) subsets and each subset can be up to (n) elements.
    //Space Complexity: O(2^n*n) for storing all subsets.
    private static List<List<Integer>> findAllSubsetsv5(ArrayList<Integer> A) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        backtracking(0, result, A, row);
        return result;
    }

    private static void backtracking(int start, List<List<Integer>> result, ArrayList<Integer> A, ArrayList<Integer> row) {
        if (start == A.size()) {
            result.add(new ArrayList<>(row));//have a deep copy
            return;
        }
        backtracking(start + 1, result, A, row); // without current element
        row.add(A.get(start)); // include current element
        backtracking(start + 1, result, A, row);
        row.remove(row.size() - 1); // backtrack
    }

    //working code, backtracking one
    private static ArrayList<ArrayList<Integer>> findAllSubsetsv3(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Collections.sort(A); // sort input for consistent subset order
        ArrayList<Integer> row = new ArrayList<>();
        backtracking(0, result, A, row);
        result.sort((a, b) -> {
            int i = 0;
            while (i < a.size() && i < b.size()) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
                i++;
            }
            return a.size() - b.size();
        });
        return result;
    }

    private static void backtracking(int start, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> A, ArrayList<Integer> row) {
        if (start == A.size()) {
            result.add(new ArrayList<>(row));//have a deep copy
            return;
        }

        backtracking(start + 1, result, A, row); // without current element
        row.add(A.get(start)); // include current element
        backtracking(start + 1, result, A, row);
        row.remove(row.size() - 1); // backtrack
    }

    //working code, backtracking approach using Deque
    private static ArrayList<ArrayList<Integer>> findAllSubsetsv2(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Collections.sort(A);
        findAllSubsets(A, 0, new ArrayDeque<>(), ans);
        Collections.sort(ans, (ArrayList<Integer> a, ArrayList<Integer> b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });
        return ans;
    }

    private static void findAllSubsets(ArrayList<Integer> A, int idx, Deque<Integer> stack, ArrayList<ArrayList<Integer>> ans) {
        if (A.size() == idx) {
            // ans.add(l);
            ans.add(new ArrayList<>(stack));//have a deep copy
            return;
        }
        stack.addLast(A.get(idx));
        findAllSubsets(A, idx + 1, stack, ans);
        stack.pollLast();// delete the last added element
        findAllSubsets(A, idx + 1, stack, ans);
    }

    //working code, bit manipulation approach
    private static ArrayList<ArrayList<Integer>> findAllSubsetsv4(ArrayList<Integer> input) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int len = input.size();
        input.sort((a, b) -> a - b);
        int totalSubsets = 1 << len; // 2^n
        for (int i = 0; i < totalSubsets; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(input.get(j));
                }
            }
            ans.add(subset);
        }
        ans.sort((a, b) -> {
            int i = 0;
            while (i < a.size() && i < b.size()) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
                i++;
            }
            return a.size() - b.size();
        });
        return ans;
    }

    //empty sequence is also a subsequence
    //using bit manipulation approach
    //iterates through all possible binary representations of numbers from 0 to 2^n - 1
    //subsequences of an array are not same as the subsequences of the same sorted array
    //not working code
    public static ArrayList<ArrayList<Integer>> findAllSubsetsv1(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int totalSubsets = (int) Math.pow(2, A.size());
        for (int i = 0; i < totalSubsets; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < A.size(); j++) {
                if (checkBit(i, j)) {
                    subset.add(A.get(j));
                }
            }
            ans.add(subset);
        }
        return ans;
    }

    public static boolean checkBit(int N, int j) {
        return ((N >> j) & 1) == 1;
    }

    public static int pow(int x, int n) {
        long a = x;
        long res = 1L;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= a;
            }
            a *= a;
            n = n >> 1;
        }
        return (int) res;
    }
}
