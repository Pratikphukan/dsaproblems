package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubstringInArray {

	public static void main(String[] args) {
		List<String> input = new ArrayList<>(Arrays.asList("dragon", "rage", "age", "savage"));
		System.out.println(findLongestSubstringInArray(input));
		String[] arr = { "dragon", "rage", "age", "savage" };
		System.out.println(findLongestSubstringInArrayv1(arr));
	}

	private static String findLongestSubstringInArrayv1(String[] arr) {
		int n = arr.length;

		// Take first word from array as reference
		String s = arr[0];
		int len = s.length();

		String res = "";

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {

				// generating all possible substrings
				// of our reference string arr[0] i.e s
				String stem = s.substring(i, j);
				int k = 1;
				for (k = 1; k < n; k++)

					// Check if the generated stem is
					// common to all words
					if (!arr[k].contains(stem))
						break;

				// If current substring is present in
				// all strings and its length is greater
				// than current result
				if (k == n && res.length() < stem.length())
					res = stem;
			}
		}

		return res;
	}

	private static String findLongestSubstringInArray(List<String> input) {
		int len = input.size();

		// Take first word from array as reference
		String s = input.get(0);
		int strLen = s.length();

		String res = "";

		for (int i = 0; i < strLen; i++) {
			for (int j = i + 1; j <= strLen; j++) {

				// generating all possible substrings
				// of our reference string arr[0] i.e s
				String stem = s.substring(i, j);
				int k = 1;
				for (k = 1; k < len; k++)

					// Check if the generated stem is
					// common to all words
					if (!input.get(k).contains(stem))
						break;

				// If current substring is present in
				// all strings and its length is greater
				// than current result
				if (k == len && res.length() < stem.length())
					res = stem;
			}
		}

		return res;
	}

}
