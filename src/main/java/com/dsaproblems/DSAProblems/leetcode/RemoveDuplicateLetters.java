package com.dsaproblems.DSAProblems.leetcode;

import java.util.*;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        //bcabc
        //abadbc
        //cbacdcbc
        String A = "cbacdcbc";
        System.out.println(removeDuplicateLettersv1(A));
        System.out.println(removeDuplicateLettersv2(A));
    }

    private static String removeDuplicateLettersv2(String A) {
        int[] count = new int[26];
        char[] chars = A.toCharArray();
        //only lowercase letters
        for (char c : chars) count[c - 'a']++;
        boolean[] inResult = new boolean[26];
        StringBuilder sb = new StringBuilder(); //behaves like a stack
        for (char c : chars) {
            int idx = c - 'a';
            count[idx]--; //decrement its remaining count
            if (inResult[idx]) continue;//skip if already in result
            while (!sb.isEmpty()) {
                char last = sb.charAt(sb.length() - 1);// last is stack top
                //if stack top is greater than c and it still appears later
                if (last > c && count[last - 'a'] > 0) {
                    inResult[last - 'a'] = false;//mark it as not in result
                    sb.deleteCharAt(sb.length() - 1);// pop the stack top
                } else break;
            }
            sb.append(c);// push to the stack
            inResult[idx] = true; //mark it in the result
        }
        return sb.toString();
    }

    //HashSet and LinkedHashSet allow one null element.
    //TreeSet and other sorted sets usually disallow null (will throw NullPointerException)
    //make sure your result is the smallest in lexicographical order among all possible results.
    //incorrect solution because it loses original ordering constraints
    private static String removeDuplicateLettersv1(String A) {
        Set<Character> set = new HashSet<>();
        for (char c : A.toCharArray()) set.add(c);
        char[] charArr = new char[set.size()];
        int i = 0;
        for (char c : set) {
            charArr[i++] = c;
        }
        Arrays.sort(charArr);
        return new String(charArr);
    }
}
