package com.dsaproblems.DSAProblems.intermediate;

public class AddBinaryStrings {

	public static void main(String[] args) {
		String A = "110";
		String B = "10";
		System.out.println(sumOfAandBv1(A, B));
	}

	private static String sumOfAandBv1(String A, String B) {
		int i = A.length() - 1;
		int j = B.length() - 1;
		int k = Math.max(i + 1, j + 1);
		char[] res = new char[k + 1];
		int sum = 0;
		while (i >= 0 || j >= 0 || sum != 0) {
			if (i >= 0)
				sum += (A.charAt(i) - '0');
			if (j >= 0)
				sum += (B.charAt(j) - '0');
			res[k] = (char) ((sum % 2) + '0');
			sum = sum / 2;
			i--;
			j--;
			k--;
		}
		if (res[0] == '1')
			return new String(res);
		int len = Math.max(A.length(), B.length());
		return new String(res, 1, len);
	}

}
