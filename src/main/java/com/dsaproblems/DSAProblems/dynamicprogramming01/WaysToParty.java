package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaysToParty {

    // 1 person->1 way
    // 2 people->2 ways
    // 3 people->4 ways
    // 4 people->4th person can singly join the group of 3 in 4 ways
    // 4th person pairs with 1(2 ways), pairs with 2(2 ways), pairs with 3(2 ways)
    public static void main(String[] args) {
        System.out.println(totalWaysToPartyv1(5));
        System.out.println(totalWaysToPartyv2(17));

        int n = 17;
        Integer[] a = new Integer[n + 1];
        for (int i = 0; i <= n; i++) {
            a[i] = -1;
        }
        List<Integer> A = new ArrayList<>(Arrays.asList(a));
        System.out.println(totalWaysToPartyv3(A, n));
    }

    private static Integer totalWaysToPartyv3(List<Integer> A, int n) {
        if (A.get(n) == -1) {
            if (n < 3) {
                A.set(n, n);
            } else {
                A.set(n, totalWaysToPartyv3(A, n - 1) + (n - 1) * totalWaysToPartyv3(A, n - 2));// two ways, the new person can enjoy alone or pair with any of the previous n-1 people
            }
        }
        return A.get(n);
    }

    private static int totalWaysToPartyv2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + (i - 1) * arr[i - 2];
            arr[i] %= 10003;
        }
        return arr[n];
    }

    private static Integer totalWaysToPartyv1(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        return totalWaysToPartyv1(i - 1) + (i - 1) * totalWaysToPartyv1(i - 2); // ways if he decides to party
        // alone->ways(n-1)
    }

}
