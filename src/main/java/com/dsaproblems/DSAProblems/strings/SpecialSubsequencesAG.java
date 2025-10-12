package com.dsaproblems.DSAProblems.strings;

import java.util.ArrayList;
import java.util.List;

public class SpecialSubsequencesAG {

    public static void main(String[] args) {
        String input = "GUGPUAGAFQBMPYAGGAAOALAELGGGAOGLGEGZ";
        System.out.println(findNoOfAGSubsequenceInInputv1(input));
        System.out.println(findNoOfAGSubsequenceInInputv2(input));
        System.out.println(findNoOfAGSubsequenceInInputv3(input));
        System.out.println(findNoOfAGSubsequenceInInputv4(input));
        System.out.println(findNoOfAGSubsequenceInInputv5(input));
    }

    //working code
    public static int findNoOfAGSubsequenceInInputv3(String input) {
        int mod = 1000000007;
        int len = input.length();
        int count_G = 0, occurrences = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (input.charAt(i) == 'G')
                count_G += 1;
            else if (input.charAt(i) == 'A')
                occurrences = (occurrences + count_G) % mod;
        }
        return occurrences % mod;
    }

    //working code
    private static int findNoOfAGSubsequenceInInputv5(String input) {
        int mod = 1000000007;
        int len = input.length();
        int count_A = 0, occurrences = 0;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'A')
                count_A += 1;
            else if (input.charAt(i) == 'G')
                occurrences = (occurrences + count_A) % mod;
        }
        return occurrences % mod;
    }

    // working and faster
    public static int findNoOfAGSubsequenceInInputv2(String input) {
        int mod = 1000000007;
        int len = input.length();
        int[] suffixCount = new int[len];
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (input.charAt(i) == 'G') {
                count += 1;
            }
            suffixCount[i] = count;
        }
        int occurrences = 0;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'A') {
                occurrences += suffixCount[i];
                occurrences %= mod;
            }
        }
        return occurrences % mod;
    }

    //working code
    private static int findNoOfAGSubsequenceInInputv4(String input) {
        int mod = 1000000007;
        int len = input.length();
        int[] prefixCount = new int[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'A') {
                count += 1;
            }
            prefixCount[i] = count;
        }
        int occurrences = 0;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'G') {
                occurrences += prefixCount[i];
                occurrences %= mod;
            }
        }
        return occurrences % mod;
    }

    public static int findNoOfAGSubsequenceInInputv1(String input) {
        List<Integer> prefixCount = new ArrayList<>();
        prefixCount.add(0);
        int mod = 1000000007;
        int len = input.length();
        for (int i = 1; i < len; i++) {
            if (input.charAt(i) == 'G') {
                prefixCount.add(prefixCount.get(i - 1) + 1);
            } else {
                prefixCount.add(prefixCount.get(i - 1));
            }
        }
        int occurrences = 0;
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == 'A') {
                occurrences += prefixCount.get(len - 1) - prefixCount.get(i);
                occurrences %= mod;
            }
        }
        return occurrences % mod;
    }

}
