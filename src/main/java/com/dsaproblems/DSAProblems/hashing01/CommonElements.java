package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonElements {

    public static void main(String[] args) {
        //1, 2, 2, 1;2, 3, 1, 2, 2
        //1,2,2,1;2,3,1,2,2
        ArrayList<Integer> A = new ArrayList<>(List.of(1, 2, 2, 1));
        ArrayList<Integer> B = new ArrayList<>(List.of(2, 3, 1, 2, 2));
        System.out.println(findCommonElements1(A, B));
        // System.out.println(findCommonElements2(A,B));
        System.out.println(findCommonElements3(A, B));
        // System.out.println(findCommonElements4(A,B));
    }

    private static ArrayList<Integer> findCommonElements4(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                if (A.get(i) == B.get(j)) {
                    ans.add(A.get(i));
                    B.set(j, null);
                    break;
                }
            }
            System.out.println(B);
        }
        return ans;
    }

    // working solution, Overall time complexity: O(n + m)
    private static ArrayList<Integer> findCommonElements3(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A.size() > B.size()) {
            ArrayList<Integer> temp = A;
            A = B;
            B = temp;

        }
        Map<Integer, Integer> freq = new HashMap<>();
        //Iterate through the smaller array and updating the map
        for (Integer key : A) {
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        //Iterate through the larger array and checking/updating the map
        for (Integer item : B) {
            if (freq.getOrDefault(item, 0) > 0) {
                ans.add(item);
                freq.put(item, freq.get(item) - 1);
            }
        }
        return ans;
    }

    // this method fails when the elements in both the arrays are not distinct, eg
    // (1,2,1)&(2,3,1,2)
    private static ArrayList<Integer> findCommonElements2(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (Integer item : A) {
            set.add(item);
        }
        for (Integer item : B) {
            if (set.contains(item)) {
                ans.add(item);
            }
        }
        return ans;
    }

    // working solution
    private static ArrayList<Integer> findCommonElements1(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        Collections.sort(A);
        Collections.sort(B);
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) > B.get(j)) {
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                ans.add(A.get(i));
                i++;
                j++;
            }
        }
        return ans;
    }
}
