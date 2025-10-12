package com.dsaproblems.DSAProblems.sorting;

import java.util.*;

public class ArrayWithConsecutiveElements {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(5000, 5000, 5000));
        // 5,17,100,11
        // 1, 3, 2, 5, 4
        // 1,2,3,4,5
        System.out.println(checkConsecutiveArrayv1(A));
        System.out.println(checkConsecutiveArrayv2(A));

        System.out.println(find(A));
    }

    private static ArrayList<Integer> find(List<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        int len = A.size();
        int i = 0;
        while (i < len) {
            ans.add(A.get(i));
            while (i + 1 < len && A.get(i + 1).equals(A.get(i))) {
                i++;
            }
            i++;
        }
        return ans;
    }

    private static int checkConsecutiveArrayv2(List<Integer> input) {
        Collections.sort(input);
        int min = input.get(0);
        for (Integer num : input) {
            min++;
            if (num != min) {
                return 0;
            }
        }
        return 1;
    }

    private static int checkConsecutiveArrayv1(List<Integer> input) {
        // int min = input.stream().min((a, b) -> a.compareTo(b)).get();
        int min = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (Integer num : input) {
            if (set.contains(num)) {
                return 0;
            }
            set.add(num);
            min = Math.min(min, num);
        }
        int firstItem = min;
        int lastItem = min + input.size() - 1;
        while (set.contains(firstItem)) {
            firstItem++;
        }
        if (firstItem > lastItem) {
            return 1;
        }
        return 0;
    }

}
