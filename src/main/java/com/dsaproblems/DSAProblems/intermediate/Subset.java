package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subset {

    public static void main(String[] args) {
        //9, -20, -11, -8, -4, 2, -12, 14, 1, -18
        //1, 2, 3
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(findAllSubsetsv1(input));
        System.out.println(findAllSubsetsv2(input));
        System.out.println(findAllSubsetsv3(input));
        System.out.println(findAllSubsetsv4(input));
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
            result.add(new ArrayList<>(row)); // add a copy
            return;
        }

        backtracking(start + 1, result, A, row); // without current element
        row.add(A.get(start)); // include current element
        backtracking(start + 1, result, A, row);
        row.remove(row.size() - 1); // backtrack
    }

    private static ArrayList<ArrayList<Integer>> findAllSubsetsv2(ArrayList<Integer> A) {
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Collections.sort(A);
        findAllSubsets(A, 0, l, ans);
        Collections.sort(ans, (ArrayList<Integer> first, ArrayList<Integer> second) -> {
            for (int i = 0; i < first.size() && i < second.size(); i++) {
                if (first.get(i) < second.get(i))
                    return -1;
                if (first.get(i) > second.get(i))
                    return 1;
            }
            if (first.size() > second.size())
                return 1;
            return -1;
        });
        return ans;
    }

    private static void findAllSubsets(ArrayList<Integer> A, int idx, ArrayList<Integer> l, ArrayList<ArrayList<Integer>> ans) {
        if (A.size() == idx) {
            // ans.add(l);
            ans.add(new ArrayList<>(l));//have a deep copy
            return;
        }
        l.add(A.get(idx));
        findAllSubsets(A, idx + 1, l, ans);
        l.remove(l.size() - 1);// delete the last added element
        findAllSubsets(A, idx + 1, l, ans);
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
