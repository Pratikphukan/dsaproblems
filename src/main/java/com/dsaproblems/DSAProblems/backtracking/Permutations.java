package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        //1,2,4
        //1,1,2
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 4));
        System.out.println(getPermuatationsv1(input));
    }

    private static ArrayList<ArrayList<Integer>> getPermuatationsv1(ArrayList<Integer> input) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        solvev1(input, output, 0);
        //solvev2(input, output, 0);
        return output;
    }

    //If you expect duplicate elements, use solvev2 to avoid duplicate
    public static void solvev2(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> ans, int idx) {
        if (A.size() == idx) {
            ans.add(new ArrayList<>(A));
            return;
        }
        HashSet<Integer> used = new HashSet<>();
        for (int i = idx; i < A.size(); i++) {
            if (used.contains(A.get(i))) continue;
            used.add(A.get(i));
            swap(A, i, idx);
            solvev2(A, ans, idx + 1);
            swap(A, i, idx);
        }
    }

    //working code
    //all the numbers in the collection are unique
    //efficient for generating all permutations of unique elements in O(n!)
    //Swap the element at idx with the element at i to fix one element at the current position.
    //Recursively call solvev1 for the next index (idx + 1).
    //Swap back to restore the original list (backtrack).
    private static void solvev1(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> ans, int idx) {
        if (A.size() == idx) {
            ans.add(new ArrayList<>(A));
            return;
        }
        for (int i = idx; i < A.size(); i++) {
            if (i != idx) swap(A, i, idx); //avoid unnecessary swaps when i == idx
            solvev1(A, ans, idx + 1);
            if (i != idx) swap(A, i, idx);
        }
    }

    private static void swap(ArrayList<Integer> A, int i, int j) {
        int temp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, temp);
    }
}
