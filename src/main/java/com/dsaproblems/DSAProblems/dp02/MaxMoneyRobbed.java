package com.dsaproblems.DSAProblems.dp02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxMoneyRobbed {
    public static void main(String[] args) {

        Integer[] a = {2, 7, 9, 3, 1};
        List<Integer> moneyInHouses = new ArrayList<>(Arrays.asList(a));
        int noOfHouses = moneyInHouses.size();

        System.out.println(maxMoneyRobbedv1(moneyInHouses, noOfHouses));

        int[] arr = new int[noOfHouses + 1];
        Arrays.fill(arr, -1);

        System.out.println(maxMoneyRobbedv2(moneyInHouses, arr, noOfHouses));

    }

    private static boolean maxMoneyRobbedv2(List<Integer> moneyInHouses, int[] arr, int noOfHouses) {
        return false;
    }

    //max amount that can be robbed from the first n houses
    private static Integer maxMoneyRobbedv1(List<Integer> moneyInHouses, int noOfHouses) {
        if (noOfHouses == 0) {
            return 0;
        }
        if (noOfHouses == 1) {
            return moneyInHouses.get(0);
        }
        return Math.max(
                maxMoneyRobbedv1(moneyInHouses, noOfHouses - 1),
                maxMoneyRobbedv1(moneyInHouses, noOfHouses - 2) + moneyInHouses.get(noOfHouses - 1));
    }
}
