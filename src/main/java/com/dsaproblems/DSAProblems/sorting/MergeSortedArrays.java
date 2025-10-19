package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortedArrays {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(-4, 3));
        // [-4,3];[-2,-2]
        // [-5, -1, 3, 7, 10, 12, 15], [-4, 0, 2, 8, 9]
        List<Integer> B = new ArrayList<>(Arrays.asList(-2, -2));
        System.out.println(mergeSortv1(A, B));
        System.out.println(mergeSortv2(A, B));
    }

    private static ArrayList<Integer> mergeSortv2(List<Integer> A, List<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) < B.get(j)) {
                ans.add(A.get(i));
                i++;
            } else if (A.get(i) >= B.get(j)) { // only > will not work because it will go into an infinite loop if both
                // values are same
                ans.add(B.get(j));
                j++;
            }
        }
        while (i < A.size()) {
            ans.add(A.get(i));
            i++;
        }
        while (j < B.size()) {
            ans.add(B.get(j));
            j++;
        }
        return ans;
    }

    private static List<Integer> mergeSortv1(List<Integer> A, List<Integer> B) {
        List<Integer> ans = new ArrayList<>();
        int idxi = 0;
        int idxj = 0;
        while (idxi < A.size() && idxj < B.size()) {
            if (A.get(idxi) < B.get(idxj)) {
                ans.add(A.get(idxi));
                idxi++;
            } else if (A.get(idxi) > B.get(idxj)) {
                ans.add(B.get(idxj));
                idxj++;
            } else {
                ans.add(A.get(idxi));
                ans.add(B.get(idxj));
                idxi += 1;
                idxj += 1;
            }
        }
        while (idxi < A.size()) {
            ans.add(A.get(idxi));
            idxi++;
        }
        while (idxj < B.size()) {
            ans.add(B.get(idxj));
            idxj++;
        }
        return ans;
    }

}
