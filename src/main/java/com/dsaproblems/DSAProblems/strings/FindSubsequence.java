package com.dsaproblems.DSAProblems.strings;

public class FindSubsequence {

    public static void main(String[] args) {
        String toFind = "bit";
        String input = "dfbkjijgbbiihbmmt";

//		String toFind = "apple";
//		String input = "appel";
        System.out.println(checkAIsAvailableInBv1(toFind, input));
        System.out.println(checkAIsAvailableInBv2(toFind, input));
    }

    private static String checkAIsAvailableInBv2(String toFind, String input) {
        int i = 0, j = 0;
        while (i < toFind.length() && j < input.length()) {
            if (toFind.charAt(i) == input.charAt(j))
                i++;
            j++;
        }
        return i == toFind.length() ? "YES" : "NO";
    }

    private static String checkAIsAvailableInBv1(String toFind, String input) {
        int j = 0;
        boolean isPresent = false;
        for (int i = 0; i < toFind.length(); i++) {
            isPresent = false;
            for (; j < input.length(); j++) {
                isPresent = toFind.charAt(i) == input.charAt(j);
                if (isPresent) {
                    j++;
                    break;
                }
            }
            if (!isPresent) {
                return "NO";
            }
        }
        return "YES";
    }
}
