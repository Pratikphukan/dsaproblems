package com.dsaproblems.DSAProblems.strings;

public class CountOccurrences {

	public static void main(String[] args) {
		String input = "bobob";
		// bobob, abobc
		String givenString = "bob";
		System.out.println(countOccurrencesOfGivenv1(input, givenString));
		System.out.println(countOccurrencesOfGivenv2(input, givenString));
	}

	private static int countOccurrencesOfGivenv2(String input, String givenString) {
		int inputLen = input.length();
		int givenLen = givenString.length();
		int countOfGivenString = 0;
		int idx = 0;
		while (idx <= inputLen - givenLen) {
			if (input.substring(idx, idx + givenLen).equals(givenString)) {
				countOfGivenString += 1;
				idx += givenLen - 1;
			} else {
				idx += 1;
			}
		}
		return countOfGivenString;
	}

	private static int countOccurrencesOfGivenv1(String input, String givenString) {
		int inputLen = input.length();
		int givenLen = givenString.length();
		int countOfGivenString = 0;
		for (int i = 0; i <= inputLen - givenLen; i++) {
			if (input.substring(i, i + givenLen).equals(givenString)) {
				countOfGivenString += 1;
			}
		}
		return countOfGivenString;
	}

	private static void countOccurrencesOfGiven(String input) {
//		int len = input.length();
//		String givenString = "bob";
////		int idx = 0;
////		for (int i = 0; i < len; i++) {
////			idx = 0;
////			while(input.charAt(i))
////		}
//		int idx = 0;
//		while (idx < input.length()) {
//			if(input.charAt(idx)==givenString.charAt(0)) {
//				while()
//			}
//		}
	}

}
