package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.List;

public class LPS {

    public static void main(String[] args) {
        String input = "aabaaba";
        System.out.println(findLPSofStringv1(input));
        System.out.println(findLPSofStringv2(input));
        System.out.println(findLPSofAllStringv2(input));
    }

    private static List<Integer> findLPSofAllStringv2(String input) {
        int size = input.length();
        int maxLength = 0;
        List<Integer> lps = new ArrayList<>();
        for (int len = 1; len <= size; len++) {
            maxLength = 0;
            for (int l = 1; l < len; l++) {
                boolean match = true;
                for (int i = 0; i < l; i++) {
                    if (input.charAt(i) != input.charAt(len - l + i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    maxLength = l;
                }
            }
            lps.add(maxLength);
        }
        return lps;
    }

    private static int findLPSofStringv2(String input) {
        int len = input.length();
        int maxLength = 0;
        for (int l = 1; l < len; l++) {
            boolean match = true;
            for (int i = 0; i < l; i++) {
                if (input.charAt(i) != input.charAt(len - l + i)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                maxLength = l;
            }
        }
        return maxLength;
    }

    private static int findLPSofStringv1(String input) {
        int len = input.length();
        String prefix;
        String suffix;
        int maxLength = 0;
        for (int i = 0; i < len - 1; i++) {
            prefix = input.substring(0, i + 1);
            suffix = input.substring(len - i - 1, len);
            if (prefix.equals(suffix)) {
                maxLength = Math.max(maxLength, prefix.length());
            }
        }
        return maxLength;
    }
}
