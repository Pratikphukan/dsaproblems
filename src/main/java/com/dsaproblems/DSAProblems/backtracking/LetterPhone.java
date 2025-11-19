package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterPhone {

    public static void main(String[] args) {
        String A = "234";
        //234 Expected Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(possibleLetterCombinationsv1(A));
    }

    //working code
    private static List<String> possibleLetterCombinationsv1(String digits) {
        List<String> result = new ArrayList<>();
        String[] mapping = new String[]{
                "0",      // 0: no mapping
                "1",      // 1: no mapping
                "abc",   // 2: mapping for digit '2'
                "def",   // 3: mapping for digit '3'
                "ghi",   // 4: mapping for digit '4'
                "jkl",   // 5: mapping for digit '5'
                "mno",   // 6: mapping for digit '6'
                "pqrs",  // 7: mapping for digit '7'
                "tuv",   // 8: mapping for digit '8'
                "wxyz"   // 9: mapping for digit '9'
        };
        backtrack(digits, 0, new StringBuilder(), result, mapping);
        return result;
    }

    private static void backtrack(String digits, int idx, StringBuilder combination, List<String> result, String[] mapping) {
        // Base case: if current combination length is equal to the digits length, add to the result list.
        if (idx == digits.length()) {
            result.add(combination.toString());
            return;
        }
        int digit = digits.charAt(idx) - '0'; //get the int value of the current digit
        String letters = mapping[digit]; //retrieve the associated letters
        for (int i = 0; i < letters.length(); i++) {
            combination.append(letters.charAt(i));
            backtrack(digits, idx + 1, combination, result, mapping);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
