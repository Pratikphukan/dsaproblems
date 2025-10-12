package com.dsaproblems.DSAProblems.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsDictionary {

	public static void main(String[] args) {
//		List<String> input = new ArrayList<>(Arrays.asList("hello", "scaler", "interviewbit"));
//		String order = "adhbcfegskjlponmirqtxwuvzy";
		List<String> input = new ArrayList<>(Arrays.asList("apply", "app", "fine"));
		String order = "qwertyuiopasdfghjklzxcvbnm";
		System.out.println(checkGivenWordsSortedLexicographicallyv1(input, order));
		System.out.println(checkGivenWordsSortedLexicographicallyv2(input, order));
	}

	// working
	private static int checkGivenWordsSortedLexicographicallyv2(List<String> input, String order) {
		int[] dictionary = new int[26];
		for (int i = 0; i < order.length(); ++i) {
			dictionary[order.charAt(i) - 'a'] = i;
		}
		String currentWord = null;
		String nextWord = null;
		int minLength = 0;
		boolean flag = false;
		for (int i = 0; i < input.size() - 1; ++i) {
			currentWord = input.get(i);
			nextWord = input.get(i + 1);
			minLength = Math.min(currentWord.length(), nextWord.length());
			flag = false;
			for (int j = 0; j < minLength; ++j) {
				int c1 = currentWord.charAt(j) - 'a';
				int c2 = nextWord.charAt(j) - 'a';
				if (c1 == c2) {
					continue;
				}
				if (dictionary[c1] < dictionary[c2]) {
					flag = true;
					break;
				}
				return 0;
			}
			if (!flag && currentWord.length() > nextWord.length()) {
				return 0;
			}
		}
		return 1;
	}

	// working
	private static int checkGivenWordsSortedLexicographicallyv1(List<String> words, String order) {
		Map<Character, Integer> charToIndex = new HashMap<>();
		for (int i = 0; i < order.length(); i++) {
			charToIndex.put(order.charAt(i), i);
		}
		StringBuilder sb = new StringBuilder();
		String previousWord = null;
		String currentWord = null;
		boolean firstWord = true;
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				sb.append((char) (charToIndex.get(word.charAt(i)) + 'a'));
			}
			currentWord = sb.toString();
			if (firstWord) {
				previousWord = currentWord;
				sb = new StringBuilder();
				firstWord = false;
				continue;
			}
			if (previousWord.compareTo(currentWord) > 0) {
				return 0;
			}
			previousWord = currentWord;
			sb = new StringBuilder();
		}
		return 1;
	}

}
