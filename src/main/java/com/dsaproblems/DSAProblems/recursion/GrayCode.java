package com.dsaproblems.DSAProblems.recursion;

import java.util.ArrayList;

public class GrayCode {

    public static void main(String[] args) {
        int n = 3; // Example for 3 bits
        System.out.println(generateGrayCodev1(n));
        System.out.println(generateGrayCodev2(n));
        System.out.println(generateGrayCodev3(n));
        System.out.println(generateGrayCodev4(n));
    }

    private static ArrayList<Integer> generateGrayCodev4(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        int total = 1 << n;
        for (int i = 0; i < total; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    private static ArrayList<Integer> generateGrayCodev3(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(1);
        if (n == 1) {
            return ans;
        }
        for (int i = 1; i < n; i++) {
            int size = ans.size();
            for (int j = size - 1; j >= 0; j--) {
                ans.add(ans.get(j) | (1 << i));
            }
        }
        return ans;
    }

    //working code, TC: O(2^n)
    private static ArrayList<Integer> generateGrayCodev2(int n) {
        int total = 1 << n;
        ArrayList<Integer> result = new ArrayList<>(total);
        result.add(0);
        for (int i = 0; i < n; i++) {
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add(result.get(j) | (1 << i));
            }
        }
        return result;
    }

    private static ArrayList<Integer> generateGrayCodev1(int A) {
        if (A == 1) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(0);
            a.add(1);
            return a;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> prev = generateGrayCodev1(A - 1);
        for (int num : prev) {
            ans.add(num << 1);
        }
        ArrayList<Integer> rev = reverse(prev);
        for (int i = 0; i < rev.size(); i++) {
            ans.add((prev.get(i) << 1) | 1);
        }
        return ans;
    }

    private static ArrayList<Integer> reverse(ArrayList<Integer> prev) {
        int s = 0, e = prev.size() - 1;
        while (s < e) {
            int temp = prev.get(s);
            prev.set(s, prev.get(e));
            prev.set(e, temp);
            s++;
            e--;
        }
        return prev;
    }
}
