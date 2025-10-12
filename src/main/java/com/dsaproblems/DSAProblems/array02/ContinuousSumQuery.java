package com.dsaproblems.DSAProblems.array02;

import java.util.ArrayList;
import java.util.List;

public class ContinuousSumQuery {

    public static void main(String[] args) {
        int B = 5;
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(List.of(1, 2, 10)));
        input.add(new ArrayList<>(List.of(2, 3, 20)));
        input.add(new ArrayList<>(List.of(2, 5, 25)));

        System.out.println(findTotalAmountv1(input, B));
        System.out.println(findTotalAmountv2(input, B));
    }

    private static ArrayList<Integer> findTotalAmountv2(ArrayList<ArrayList<Integer>> input, int B) {
        int[] coins = new int[B];
        for (ArrayList<Integer> devotee : input) {
            int left = devotee.get(0) - 1;
            int right = devotee.get(1) - 1;
            int amount = devotee.get(2);
            coins[left] += amount;
            if (right + 1 < B) coins[right + 1] -= amount;
        }
        for (int i = 1; i < B; i++) {
            coins[i] += coins[i - 1];
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int c : coins) result.add(c);
        return result;
    }

    private static ArrayList<Integer> findTotalAmountv1(ArrayList<ArrayList<Integer>> input, int B) {
        ArrayList<Integer> coins = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            coins.add(0);
        }
        int left = -1;
        int right = -1;
        int amount = 0;
        for (List<Integer> devotee : input) {
            left = devotee.get(0) - 1;
            right = devotee.get(1) - 1;
            amount = devotee.get(2);
            coins.set(left, coins.get(left) + amount);
            if (right + 1 < B)
                coins.set(right + 1, coins.get(right + 1) - amount);
        }
        for (int i = 1; i < coins.size(); i++) {
            coins.set(i, coins.get(i) + coins.get(i - 1));
        }
        return coins;
    }
}
