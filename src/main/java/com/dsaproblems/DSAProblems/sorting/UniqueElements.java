package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UniqueElements {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 1, 3));
        System.out.println(makeElementsUniquev1(input));
        System.out.println(makeElementsUniquev2(input));
        //[51,6,10,8,22,61,56,48,88,85,21,98,81,76,71,68,18,6,14,23,72,18,56,30,97,100,81,5,99,2,85,67,46,32,66,51,76,53,36,31,81,56,26,75,69,54,54,54,83,41,86,48,7,32,85,23,47,23,18,45,79,95,73,15,55,16,66,73,13,85,14,80,39,92,66,20,22,25,34,14,51,14,17,10,100,35,9,83,31,60,24,37,69,62]
    }

    private static int makeElementsUniquev2(List<Integer> A) {
        int n = A.size();
        Collections.sort(A);
        int steps = 0, i = 1, j = 0;
        while (j < n) {
            if (i >= A.get(j)) {
                steps += (i - A.get(j++));
                i += 1;
            } else {
                i = A.get(j) + 1;
                j += 1;
            }
        }
        return steps;
    }

    //input has positive integers
    private static int makeElementsUniquev1(List<Integer> A) {
        Collections.sort(A); //Sort the list so duplicates are adjacent
        int prev = A.get(0);
        int count = 0;
        for (int i = 1; i < A.size(); i++) {
            int curr = A.get(i);
            if (curr <= prev) { //a common doubt might be how prev>curr because previously, prev can be incremented by 1
                count += (prev - curr) + 1;//Add the number of increments to a counter.
                curr = prev + 1;
            }
            prev = curr;
        }
        return count;
    }
}
