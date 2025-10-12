package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.List;

public class PickFromBothSides {

    public static void main(String[] args) {
        List<Integer> a = List.of(5, -2, 3, 1, 2);
        // (-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961,
        // -509, -5, 942, -173, 436, -609, -396, 902, -847, -708, -618, 421, -284, 718,
        // 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322, -667,
        // 673, -336, 141, 711, -747, -132, 547, 644, -338, -243, -963, -141, -277, 741,
        // 529, -222, -684, 35)
        // (5,-2,3,1,2)
        ArrayList<Integer> A = new ArrayList<>(a);
        // System.out.println(A.size());
        int B = 3;
        // System.out.println(maxPossibleSum(A,B));
        System.out.println(maxPossibleSum1(A, B));
        System.out.println(maxPossibleSum2(A, B));
    }

    // perfect solution, passes all test cases
    private static int maxPossibleSum2(ArrayList<Integer> A, int B) {
        int total = 0;
        for (Integer item : A) {
            total += item;
        }
        int k = A.size() - B;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += A.get(i);
        }
        int min = sum;
        for (int i = 1; i <= A.size() - k; i++) {
            sum += (A.get(i + k - 1) - A.get(i - 1));
            if (sum < min) {
                min = sum;
            }
        }
        return total - min;
    }

    //workinng solution, passes all test cases
    private static int maxPossibleSum1(ArrayList<Integer> A, int B) {
        int n = A.size();
        int sum = 0;
        for (int i = 0; i < B; i++) {
            sum += A.get(i);
        }
        int max = sum;
        for (int i = 0; i < B; i++) {
            sum += (A.get(n - 1 - i) - A.get(B - 1 - i));
            max = Math.max(max, sum);
        }
        return max;
    }

    // wrong approach is greedy approach which is below
    private static int maxPossibleSum(ArrayList<Integer> A, int B) {
        int l = 0, h = A.size() - 1;
        int sum = 0;
        int i = 0;
        while (i < B) {
            if (A.get(l) > A.get(h)) {
                sum += A.get(l);
                l++;
            } else {
                sum += A.get(h);
                h--;
            }
            i++;
        }
        return sum;
    }

}
