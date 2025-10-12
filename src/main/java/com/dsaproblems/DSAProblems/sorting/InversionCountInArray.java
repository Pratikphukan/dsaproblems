package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InversionCountInArray {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 4, 1, 2));
        System.out.println(solve(A));
    }

    public static int solve(ArrayList<Integer> A) {
        return countPairs(A, 0, A.size() - 1);
    }

    public static int countPairs(ArrayList<Integer> C, int s, int e) {
        if (s == e) {
            return 0;
        }
        int mod = 1000000007;
        int m = (s + e) / 2;
        int l = countPairs(C, s, m);
        int r = countPairs(C, m + 1, e);
        return (l + r + merge(C, s, m, e)) % mod;
    }

    public static int merge(ArrayList<Integer> B, int s, int m, int e) {
        int i = s, j = m + 1;
        int mod = 1000000007;
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while (i <= m && j <= e) { // mistake is considering the size
            if (B.get(i) <= B.get(j)) {
                ans.add(B.get(i));
                i++;
            } else if (B.get(i) > B.get(j)) {
                ans.add(B.get(j));
                j++;
                count += m - i + 1;
                count = count % mod;
            }
        }
        while (i <= m) {
            ans.add(B.get(i));
            i++;
        }
        while (j <= e) {
            ans.add(B.get(j));
            j++;
        }
        // we got the sorted data in the temporary array
        for (int k = 0; k < (e - s + 1); k++) {
            B.set(s + k, ans.get(k));
        }
        return count % mod;
    }
}
