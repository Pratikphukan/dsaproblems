package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyingCandies {

    public static void main(String[] args) {
        // 2|5|10|99
        // 1, 2, 3|2, 2, 10|2, 3, 9|8
        List<Integer> packets = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> sweetness = new ArrayList<>(Arrays.asList(2, 2, 10));
        List<Integer> costs = new ArrayList<>(Arrays.asList(2, 3, 9)); // cost of each packet
        int nibbles = 8;
        int totalPackets = packets.size();
        System.out.println(findMaximumSweetnessPossiblev1(packets, sweetness, costs, totalPackets, nibbles));


        System.out.println(findMaximumSweetnessPossiblev2(packets, sweetness, costs, nibbles));
    }

    private static int findMaximumSweetnessPossiblev2(List<Integer> packets, List<Integer> sweetness, List<Integer> costs, int nibbles) {
        int n = packets.size();
        int[] dp = new int[nibbles + 1];
        dp[0] = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            sweetness.set(i, packets.get(i) * sweetness.get(i)); //calculate total sweetness for each packet
        }

        for (int nibble = 1; nibble <= nibbles; nibble++) {
            for (int i = 0; i < n; i++) {
                if (nibble >= costs.get(i)) {
                    int currAns = sweetness.get(i) + dp[nibble - costs.get(i)];
                    ans = Math.max(ans, currAns);
                    dp[nibble] = ans;
                }
            }
        }
        return dp[nibbles];
    }

    private static int findMaximumSweetnessPossiblev1(List<Integer> packets, List<Integer> sweetness,
                                                      List<Integer> costs, int totalPackets, int nibbles) {
        if (nibbles <= 0 || totalPackets <= 0) {
            return 0;
        }
        int amount = findMaximumSweetnessPossiblev1(packets, sweetness, costs, totalPackets - 1, nibbles);
        if (nibbles >= costs.get(totalPackets - 1) * packets.get(totalPackets - 1)) {
            amount = Math.max(amount,
                    findMaximumSweetnessPossiblev1(packets, sweetness, costs, totalPackets,
                            nibbles - costs.get(totalPackets - 1) * packets.get(totalPackets - 1))
                            + packets.get(totalPackets - 1) * sweetness.get(totalPackets - 1));
        }
        return amount;
    }

}
