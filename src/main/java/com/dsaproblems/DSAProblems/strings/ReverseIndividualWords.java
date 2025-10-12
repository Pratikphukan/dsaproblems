package com.dsaproblems.DSAProblems.strings;

public class ReverseIndividualWords {

	public static void main(String[] args) {
		String input = "bwroafq rfmy eimspekey w wnzjh qisjiabv ya hncn mazvb pfwlcsnkqz muiapt nnvwwx rp bsypbqu ymg bjwapykfil";
		System.out.println(reverseIndividualWordsInStringv1(input));
		System.out.println(reverseIndividualWordsInStringv2(input));
	}

	private static String reverseIndividualWordsInStringv2(String input) {
		StringBuilder word = new StringBuilder();
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ') {
				word.insert(0, c);
			} else {
				ans.append(word + " ");
				word = new StringBuilder();
			}
		}
		ans.append(word);
		return ans.toString();
	}

	private static String reverseIndividualWordsInStringv1(String input) {
		StringBuilder word = new StringBuilder();
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ') {
				word.insert(0, c);
			} else {
				ans.append(word + " ");
				word.replace(0, word.length(), "");
			}
		}
		ans.append(word);
		return ans.toString();
	}

}
