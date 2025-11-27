package com.dsaproblems.DSAProblems.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static void main(String[] args) {
        //applepenapple|["apple", "pen"]
        //leetcode|["leet", "code"]
        String s = "applepenapple";
        List<String> dict = Arrays.asList("apple", "pen");
        System.out.println(wordBreakv1(s, dict));
    }

    private static boolean wordBreakv1(String s, List<String> dict) {
        Set<String> uniqueWords = new HashSet<>(dict);
        // dp[i] will be true if the substring s[0...i-1] can be segmented into dictionary words
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // Loop over the length of the string from 1 to s.length()
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && uniqueWords.contains(s.substring(j, i))) { //substring begins at the specified beginIndex and extends to the character at index endIndex - 1
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
