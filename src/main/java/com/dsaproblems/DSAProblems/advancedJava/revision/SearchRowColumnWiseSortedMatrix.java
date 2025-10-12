package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchRowColumnWiseSortedMatrix {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        A.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        A.add(new ArrayList<>(Arrays.asList(7, 8, 9)));
        int B = 2;
        System.out.println(searchRowColumnWiseSortedMatrixv1(A, B));
        System.out.println(searchRowColumnWiseSortedMatrixv2(A, B));
    }

    //working code
    private static int searchRowColumnWiseSortedMatrixv2(ArrayList<ArrayList<Integer>> A, int B) {
        int rlen = A.size(), clen = A.get(0).size();
        int i = rlen - 1, j = 0, min = Integer.MAX_VALUE;
        while (i >= 0 && j < clen) {
            int val = A.get(i).get(j);
            if (val < B) {
                j++;
            } else if (val > B) {
                i--;
            } else {
                min = Math.min(min, (i + 1) * 1009 + (j + 1));
                i--;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    //working code
    private static int searchRowColumnWiseSortedMatrixv1(ArrayList<ArrayList<Integer>> A, int B) {
        int rlen = A.size(), clen = A.get(0).size();
        int i = 0, j = clen - 1, min = Integer.MAX_VALUE;
        while (i < rlen && j >= 0) {
            int val = A.get(i).get(j);
            if (val < B) {
                i++;
            } else if (val > B) {
                j--;
            } else {
                min = Math.min(min, (i + 1) * 1009 + (j + 1));
                j--;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
