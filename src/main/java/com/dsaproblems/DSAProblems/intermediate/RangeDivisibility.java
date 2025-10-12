package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.List;


public class RangeDivisibility {
    public static void main(String[] args) {
        //6,3,3,6,7,8,7,3,7|[2,6][4,7][6,7]
        //14, 21, 35, 10, 7, 49, 28
        ArrayList<Integer> A = new ArrayList<>(List.of(6, 3, 3, 6, 7, 8, 7, 3, 7));
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        B.add(new ArrayList<>(List.of(2, 6))); // Range [0, 3]
        B.add(new ArrayList<>(List.of(4, 7))); // Range [2, 5]
        B.add(new ArrayList<>(List.of(6, 7))); // Range [1, 6]

        RangeDivisibility rd = new RangeDivisibility();
        ArrayList<Integer> result = rd.solvev1(A, B);

        System.out.println(result); // Output: [3, 3, 5]
    }

    public ArrayList<Integer> solvev1(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int len = A.size();
        ArrayList<Integer> prefix = new ArrayList<>();
        prefix.add(A.get(0) % 7 == 0 ? 1 : 0);
        for (int i = 1; i < len; i++) {
            prefix.add(prefix.get(i - 1) + (A.get(i) % 7 == 0 ? 1 : 0));
        }
        int count = 0;
        int left, right;
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            left = query.get(0);
            right = query.get(1);
            count = prefix.get(right) - (left > 0 ? prefix.get(left - 1) : 0);
            ans.add(count);
        }
        return ans;
    }

    private ArrayList<Integer> solvev2(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        ArrayList<Integer> prefixCount = new ArrayList<>(n);
        ArrayList<Integer> ans = new ArrayList<>();

        // Precompute the prefix count of numbers divisible by 7
        prefixCount.add(A.get(0) % 7 == 0 ? 1 : 0);
        for (int i = 1; i < n; i++) {
            prefixCount.add(prefixCount.get(i - 1) + (A.get(i) % 7 == 0 ? 1 : 0));
        }

        // Process each query
        for (ArrayList<Integer> query : B) {
            int L = query.get(0);
            int R = query.get(1);
            int count = prefixCount.get(R) - (L > 0 ? prefixCount.get(L - 1) : 0);
            ans.add(count);
        }

        return ans;
    }


    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            count = 0;
            for (int i = query.get(0); i <= query.get(1); i++) {
                if (A.get(i) % 7 == 0)
                    count++;
            }
            ans.add(count);
        }
        return ans;
    }
}
