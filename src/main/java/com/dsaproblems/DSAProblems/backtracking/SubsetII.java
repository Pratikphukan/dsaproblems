package com.dsaproblems.DSAProblems.backtracking;

import java.util.*;

public class SubsetII {

    public static void main(String[] args) {
        //8, 10, 6, 11, 1, 16, 8
        //1, 2, 2
        //1, 1, 2
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 1, 2));
        System.out.println(findAllSubsetsv1(input));
        System.out.println(findAllSubsetsv2(input));
        System.out.println(findAllSubsetsv3(input));
    }

    private static ArrayList<ArrayList<Integer>> findAllSubsetsv1(ArrayList<Integer> A) {
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

    //working code
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

    //Each element can be either included or excluded, so there are (2^n) possible subsets for (n) elements.
    //Due to duplicates, the while loop skips over consecutive equal elements, but in the worst case (all unique), it still explores all (2^n) branches.
    //TC: O(2^n*n) —> generating all subsets and copying each subset (up to size (n))
    //The recursion stack can go up to (O(n)) depth
    private static void findAllSubsets(ArrayList<Integer> A, int idx, Deque<Integer> stack, ArrayList<ArrayList<Integer>> ans) {
        if (A.size() == idx) {
            // ans.add(l);
            ans.add(new ArrayList<>(stack));//have a deep copy
            return;
        }
        stack.addLast(A.get(idx));
        findAllSubsets(A, idx + 1, stack, ans);
        stack.pollLast();// delete the last added element
        while (idx + 1 < A.size() && A.get(idx).equals(A.get(idx + 1))) {
            idx++;
        }
        findAllSubsets(A, idx + 1, stack, ans);
    }

    //just info-> The difference is that HashSet does not preserve insertion order, while LinkedHashSet does
    //working code
    //Time Complexity (TC):
    //Generates all (2^n) possible subsets for n elements.
    //Each subset can take up to O(n) time to construct and copy.
    //Hashing and adding to the set is (O(n)) per subset.
    //Overall TC: (O(2^n*n)) (subset generation dominates; sorting is similar order).
    //
    //Space Complexity (SC):
    //Stores up to (2^n) unique subsets, each up to size (n).
    //The set and result list both use (O(2^n \cdot n)) space.
    //Overall SC: (O(2^n*n))
    private static ArrayList<ArrayList<Integer>> findAllSubsetsv3(ArrayList<Integer> input) {
        Set<List<Integer>> uniqueSubsets = new HashSet<>();
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
            uniqueSubsets.add(subset);
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (List<Integer> set : uniqueSubsets) {
            ans.add(new ArrayList<>(set));
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
}
