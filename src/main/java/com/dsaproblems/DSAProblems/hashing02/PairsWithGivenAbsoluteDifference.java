package com.dsaproblems.DSAProblems.hashing02;

import java.util.*;

public class PairsWithGivenAbsoluteDifference {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(6, 4, 8, 2));
        int targetdifference = 2;
        System.out.println(findCountPairsWithGivenDiffv1(input, targetdifference));
        System.out.println(findCountPairsWithGivenDiffv2(input, targetdifference));
    }


    //To count unique pairs with the given absolute difference,
    // use a single pass and check only num + target (since all elements are unique and positive)
    private static int findCountPairsWithGivenDiffv2(List<Integer> input, int target) {
        Set<Integer> set = new HashSet<>(input);
        int count = 0;
        for (Integer num : input) {
            if (set.contains(num + target)) {
                count++;
            }
        }
        return count;
    }

    //why this works?
    //you have to find the absolute difference, target is always positive
    //no possibility of k = 0, as all elements are distinct and positive
    private static int findCountPairsWithGivenDiffv1(List<Integer> input, int target) {
        Set<Integer> set = new HashSet<>(input);
        int count = 0;
        for (Integer num : input) {
            if (set.contains(num + target) && set.contains(num - target)) {
                count += 2;
                set.remove(num);
            } else if (set.contains(num + target) || set.contains(num - target)) {
                count += 1;
                set.remove(num);
            }
        }
        return count;
    }
}
