package com.dsaproblems.DSAProblems.intermediate;

public class StringOperations {

    public static void main(String[] args) {
        //aeiOUz
        //AbcaZeoB
        //lLdfRVCgbkND
        String input = "AbcaZeoB";
        System.out.println(executeOperationsv1(input));
        System.out.println(executeOperationsv2(input));
    }

    //working code, better
    private static String executeOperationsv2(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Only process lowercase letters
            if (c >= 'a' && c <= 'z') {
                // Check if it's a vowel
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    result.append('#');
                } else {
                    result.append(c);
                }
            }
        }
        return result.append(result).toString();
    }

    //working code
    private static String executeOperationsv1(String input) {
        StringBuilder inputsb = new StringBuilder(input);
        for (int i = inputsb.length() - 1; i >= 0; i--) {
            if (inputsb.charAt(i) >= 'A' && inputsb.charAt(i) <= 'Z') {
                inputsb.deleteCharAt(i);
            }
            //check the length after each deletion to avoid index out of bounds errors
            if (i < inputsb.length() && (inputsb.charAt(i) == 'a' || inputsb.charAt(i) == 'e' || inputsb.charAt(i) == 'i' || inputsb.charAt(i) == 'o' || inputsb.charAt(i) == 'u'))
                inputsb.setCharAt(i, '#');
        }

        return inputsb.append(inputsb).toString();
    }
}
