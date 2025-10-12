package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array of positive integers A, two integers appear only once,
 * and all the other integers appear twice. Find the two integers that
 * appear only once.
 */
public class SingleNumberIII {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 2, 3, 1, 2, 4);
        System.out.println(findUniqueIntegersv1(input));
        System.out.println(findUniqueIntegersv2(input));
        System.out.println(findUniqueIntegersv3(input));
    }

    //00000000 00000000 00000000 00000111->7
    //11111111 11111111 11111111 11111001->-7
    private static ArrayList<Integer> findUniqueIntegersv3(List<Integer> A) {
        int xor = 0;
        for (int num : A) {
            xor ^= num;
        }
        int diffBit = xor & (-xor); // Isolate rightmost set bit
        int set = 0, unset = 0;
        for (int num : A) {
            if ((num & diffBit) == 0) unset ^= num;
            else set ^= num;
        }
        return set > unset ? new ArrayList<>(Arrays.asList(unset, set)) :
                new ArrayList<>(Arrays.asList(set, unset));
    }

    // working
    // in the xor of two elements, the number of 1s denote the bit difference at
    // that index
    private static ArrayList<Integer> findUniqueIntegersv2(List<Integer> input) {
        int xor = input.parallelStream().reduce(0, (acc, element) -> element ^ acc);
        int i = 0;
        for (; i < 31; i++) {
            if (((xor >> i) & 1) == 1) {
                break;
            }
        }
        final int idx = i;
        int set = input.parallelStream().filter(num -> ((num >> idx) & 1) == 1).reduce(0, (acc, num) -> num ^ acc);
        int unset = input.parallelStream().filter(num -> ((num >> idx) & 1) == 0).reduce(0, (acc, num) -> num ^ acc);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(set > unset ? unset : set);
        ans.add(set > unset ? set : unset);
        return ans;
    }

    // working
    private static ArrayList<Integer> findUniqueIntegersv1(List<Integer> input) {
        ArrayList<Integer> ans = new ArrayList<>();
        int a = 0;
        for (int item : input) {
            a = a ^ item;
        }
        int i = 0;
        for (; i < 31; i++) {
            if (((a >> i) & 1) == 1) {
                break;
            }
        }
        int set = 0, unset = 0;
        for (int j = 0; j < input.size(); j++) {
            if (((input.get(j) >> i) & 1) == 1) {
                set = set ^ input.get(j);
            } else {
                unset = unset ^ input.get(j);
            }
        }
        if (set > unset) {
            ans.add(unset);
            ans.add(set);
        } else {
            ans.add(set);
            ans.add(unset);
        }
        return ans;
    }

}
