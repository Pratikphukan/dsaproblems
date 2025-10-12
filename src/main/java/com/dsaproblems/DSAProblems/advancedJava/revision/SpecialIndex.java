package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialIndex {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 6, 4));
        System.out.println(findCountOfIndicesv1(input));
        System.out.println(findCountOfIndicesv2(input));
        System.out.println(findCountOfIndicesv3(input));
    }

    private static int findCountOfIndicesv2(List<Integer> A) {
        int n = A.size();
        int totalEven = 0, totalOdd = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) totalEven += A.get(i);
            else totalOdd += A.get(i);
        }
        int count = 0, prefixEven = 0, prefixOdd = 0;
        for (int i = 0; i < n; i++) {
            int curr = A.get(i);
            // Remove A[i] and shift indices after i
            int even = prefixEven + (totalOdd - prefixOdd);
            int odd = prefixOdd + (totalEven - prefixEven - (i % 2 == 0 ? curr : 0));
            if (i % 2 == 0) {
                even -= curr;
            } else {
                odd -= curr;
            }
            if (even == odd) count++;
            if (i % 2 == 0) prefixEven += curr;
            else prefixOdd += curr;
        }
        return count;
    }

    //working and optimal
    private static int findCountOfIndicesv3(List<Integer> A) {
        int len = A.size();
        int[] pfEven = new int[len];
        int[] pfOdd = new int[len];
        pfEven[0] = A.get(0);
        pfOdd[0] = 0;
        for (int i = 1; i < len; i++) {
            pfEven[i] = pfEven[i - 1] + (i % 2 == 0 ? A.get(i) : 0);
            pfOdd[i] = pfOdd[i - 1] + (i % 2 == 1 ? A.get(i) : 0);
        }
        int count = 0;
        int evenSum = 0, oddSum = 0;
        //For each index, simulate removing that element and calculate the new even and odd sums using the prefix arrays.
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                evenSum = pfOdd[len - 1] - pfOdd[0];
                oddSum = pfEven[len - 1] - pfEven[0];
            } else if (i == len - 1) {
                evenSum = pfEven[len - 2];
                oddSum = pfOdd[len - 2];
            } else {
                evenSum = pfEven[i - 1] + pfOdd[len - 1] - pfOdd[i];
                oddSum = pfOdd[i - 1] + pfEven[len - 1] - pfEven[i];
            }
            if (oddSum == evenSum) count += 1;
        }
        return count;
    }

    private static int findCountOfIndicesv1(List<Integer> A) {
        ArrayList<Integer> pfeven = new ArrayList<>();
        pfeven.add(A.get(0));
        for (int i = 1; i < A.size(); i++) {
            if (i % 2 == 1) {
                pfeven.add(pfeven.get(i - 1));
            } else {
                pfeven.add(pfeven.get(i - 1) + A.get(i));
            }
        }
        ArrayList<Integer> pfodd = new ArrayList<>();
        pfodd.add(0);
        for (int i = 1; i < A.size(); i++) {
            if (i % 2 == 0) {
                pfodd.add(pfodd.get(i - 1));
            } else {
                pfodd.add(pfodd.get(i - 1) + A.get(i));
            }
        }
        int count = 0;
        int evenSum = pfodd.get(A.size() - 1) - pfodd.get(0);
        int oddSum = pfeven.get(A.size() - 1) - pfeven.get(0);
        if (evenSum == oddSum) {
            count++;
        }
        evenSum = pfodd.get(A.size() - 2);
        oddSum = pfeven.get(A.size() - 2);
        if (oddSum == evenSum) {
            count++;
        }
        for (int i = 1; i < A.size() - 1; i++) {
            evenSum = pfeven.get(i - 1) + pfodd.get(A.size() - 1) - pfodd.get(i);
            oddSum = pfodd.get(i - 1) + pfeven.get(A.size() - 1) - pfeven.get(i);
            if (evenSum == oddSum) {
                count++;
            }
        }
        return count;
    }
}
