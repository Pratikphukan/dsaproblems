package com.dsaproblems.DSAProblems.aqr;

import java.util.Arrays;
import java.util.Comparator;

public class RearrangeWords {

	public static void main(String[] args) {
		// To in the his the desert desert soldier decided dessert.
		// A is buffalo buffalo buffalo buffalo buffalo buffalo buffalo buffalo correct
		// sentence grammatically.
		// It is the hottest sun in the beach.
		String input = "It is the hottest sun in the beach.";
		System.out.println(arrangeWords(input));
		System.out.println(arrangeWordsv1(input));
		System.out.println(arrangeWordsv2(input));
	}

	private static String arrangeWordsv2(final String sentence) {
//		String[] s = sentence.toLowerCase().replaceAll("^\\.|\\.$", "").split(" ");
//		System.out.println(s.toString());
//		Arrays.sort(s, (a, b) -> a.length() - b.length());
//		String ans = String.join(" ", s);
//		return Character.toUpperCase(ans.charAt(0)) + ans.substring(1) + ".";

		String text = sentence;
		String[] s = text.toLowerCase().replace(".", "").split(" ");
		Arrays.sort(s, (a, b) -> a.length() - b.length());
		String ans = String.join(" ", s);
		return Character.toUpperCase(ans.charAt(0)) + ans.substring(1) + ".";
	}

	private static String arrangeWordsv1(String sentence) {
		sentence = sentence.replace(sentence.charAt(0) + "", (char) (sentence.charAt(0) + 32) + "");
		String[] arr = sentence.split(" ");
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		});
		StringBuilder str = new StringBuilder(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			str.append(" " + arr[i]);
		}
		sentence = (char) (str.charAt(0) - 32) + str.substring(1, str.length());
		return sentence;
	}

	public static String arrangeWords(String text) {
		text = text.replace(text.charAt(0) + "", (char) (text.charAt(0) + 32) + "");

		String[] arr = text.split(" ");
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		});

		StringBuilder str = new StringBuilder(arr[0]);

		for (int i = 1; i < arr.length; i++)
			str.append(" " + arr[i]);

		text = (char) (str.charAt(0) - 32) + str.substring(1, str.length());

		return text;
	}

}
