package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributeCandy {

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 5, 2, 1);
        ArrayList<Integer> A = new ArrayList<>(a);
        System.out.println(distributeCandiesv1(A));
        System.out.println(distributeCandiesv2(A));
        System.out.println(distributeCandiesv3(A));
    }

    private static int distributeCandiesv3(ArrayList<Integer> A) {
        int n = A.size();
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        //For each child from left to right, if the current child's rating is higher than
        //the previous, give one more candy than the previous child
        for (int i = 1; i < n; i++) {
            if (A.get(i) > A.get(i - 1))
                candies[i] = candies[i - 1] + 1;
        }
        //For each child from right to left, if the current child's rating is higher than
        //the next, ensure they have more candies than the next child (using Math.max to
        //keep the higher value)
        for (int i = n - 2; i >= 0; i--) {
            if (A.get(i) > A.get(i + 1)) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int s = 0;
        for (int i = 0; i < n; i++)
            s += candies[i];
        return s;
    }

    private static int distributeCandiesv2(ArrayList<Integer> A) {
        int n = A.size();
        int[] candies = new int[n + 1];
        Arrays.fill(candies, 1);
        for (int i = 2; i <= n; i++) {
            if (A.get(i - 1) > A.get(i - 2))
                candies[i] = candies[i - 1] + 1;
        }
        for (int i = n - 1; i >= 1; i--) {
            if (A.get(i - 1) > A.get(i)) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int s = 0;
        for (int i = 1; i <= n; i++)
            s += candies[i];
        return s;
    }

    private static int distributeCandiesv1(ArrayList<Integer> A) {
        ArrayList<Integer> left = new ArrayList<>();
        left.add(1);
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) > A.get(i - 1)) {
                left.add(left.get(i - 1) + 1);
            } else {
                left.add(1);
            }
        }
        System.out.println(left);
        ArrayList<Integer> right = new ArrayList<>();
        right.add(1);
        for (int i = A.size() - 2; i >= 0; i--) {
            if (A.get(i) > A.get(i + 1)) {
                right.add(0, right.get(0) + 1);
            } else {
                right.add(0, 1);
            }
        }
        System.out.println(right);
        int sum = 0;
        for (int i = 0; i < A.size(); i++) {
            sum += Math.max(left.get(i), right.get(i));
        }
        return sum;
    }

}
