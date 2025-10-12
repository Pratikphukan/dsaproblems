package com.dsaproblems.DSAProblems.strings;

import java.util.Deque;
import java.util.LinkedList;

public class ReverseWordsInString {

    public static void main(String[] args) {
        // String input = "Coding Simplified is nice";
        // String input = "e jrkeymu coklj si vohl slje sxtpf xvak p kicqxoqaof
        // uqwftidoi xixfvqbjey n tpkh ylqllzln ljo gvozwv";
        String input = " bwroafq rfmy eimspekey w wnzjh qisjiabv ya hncn mazvb pfwlcsnkqz muiapt nnvwwx rp bsypbqu ymg bjwapykfil";
        System.out.println(reverseWordsInStringv1(input));
        System.out.println(reverseWordsInStringv2(input));
        System.out.println(reverseWordsInStringv3(input));
        System.out.println(reverseWordsInStringv4(input));
    }

    public static String reverseWordsInStringv4(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        Deque<String> stack = new LinkedList<>();
        StringBuilder currentWord = new StringBuilder();
        String modifiedInput = input.trim();
        for (int i = 0; i < modifiedInput.length(); i++) {
            char c = modifiedInput.charAt(i);
            if (c != ' ') {
                currentWord.append(c);
            } else {
                stack.addFirst(currentWord.toString());
                currentWord.setLength(0);
            }
        }
        stack.addFirst(currentWord.toString());
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.removeFirst());
            if (!stack.isEmpty()) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String reverseWordsInStringv3(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.trim().split("\\s+"); //\s->a whitespace character, s+	Matches any string that contains at least one space
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0)
                result.append(" ");
        }
        return result.toString();
    }

    public static String reverseWordsInStringv2(String input) {
        StringBuilder word = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        String modifiedInput = input.trim();
        int len = modifiedInput.length();
        for (int i = 0; i < len; i++) {
            char c = modifiedInput.charAt(i);
            if (c != ' ') {
                word.append(c);
            } else {
                ans.insert(0, " " + word);
                //word.replace(0, word.length(), ""); //better way to clear the StringBuilder
                word.setLength(0);
            }
        }
        ans.insert(0, word);
        return ans.toString();
    }

    public static String reverseWordsInStringv1(String input) {
        StringBuilder word = new StringBuilder();
        StringBuilder reversedWordsInString = new StringBuilder();
        String modifiedInput = input.trim();
        int len = modifiedInput.length();
        for (int i = 0; i < len; i++) {
            char c = modifiedInput.charAt(i);
            if (c != ' ') {
                word.append(c);
            } else {
                reversedWordsInString.insert(0, " " + word);
                word = new StringBuilder(); //very inefficient
            }
        }
        reversedWordsInString.insert(0, word);
        return reversedWordsInString.toString();
    }

}
