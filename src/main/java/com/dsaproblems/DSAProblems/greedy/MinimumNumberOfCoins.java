package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfCoins {

    public static void main(String[] args) {
        int input = 894;
        System.out.println(minNoOfCoinsRequiredv1(input));
        System.out.println(minNoOfCoinsRequiredv2(input));
        System.out.println(minNoOfCoinsRequiredv3(input));
    }

    //Time Complexity (TC): O(log_5(input)) since the input is divided by 5 in each iteration. For input = 2 * 10^9, it runs in just 14 iterations.
    //Space Complexity (SC): O(1) auxiliary space. It requires absolutely no heap/ArrayList allocations or precomputation.
    //The Power-of-5 Analogy to Base-10
    //If your coin denominations were powers of 10 (1, 10, 100, 1000...), the minimum number of coins to make 384 is:
    //3 coins of 100
    //8 coins of 10
    //4 coins of 1
    //Total Coins: 3 + 8 + 4 = 15 (exactly the sum of the digits of 384 in base 10).
    //We never use 10 or more coins of value 10^i because we can replace them with a single 10^(i+1) coin, reducing the total coin count.
    private static int minNoOfCoinsRequiredv3(int input) {
        int ans = 0;
        while (input > 0) {
            ans += input % 5;
            input /= 5;
        }
        return ans;
    }

    private static int minNoOfCoinsRequiredv2(int input) {
        List<Integer> denominations = new ArrayList<>();
        long val = 1;
        while (val <= 2000000000) {
            denominations.add((int) val);
            val = val * 5;
        }

        int ans = 0;
        int len = denominations.size();
        for (int i = len - 1; i >= 0; i--) {
            ans += input / denominations.get(i);
            input = input % denominations.get(i);
        }
        return ans;
    }

    private static int minNoOfCoinsRequiredv1(int input) {
        List<Integer> denominations = new ArrayList<>();
        int idx = 1;
        long value = 1;
        while (value <= Integer.MAX_VALUE) {
            denominations.add((int) value);
            value = (long) Math.pow(5, idx);
            idx++;
        }
        // Collections.sort(denominations, Collections.reverseOrder());
        int len = denominations.size();
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            while (input >= denominations.get(i)) {
                // System.out.println(denominations.get(i));
                input -= denominations.get(i);
                count++;
            }
        }
        return count;
    }

}
