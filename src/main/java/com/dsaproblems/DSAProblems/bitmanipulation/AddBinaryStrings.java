package com.dsaproblems.DSAProblems.bitmanipulation;

public class AddBinaryStrings {

	public static void main(String[] args) {
		String a = "11"; // "1010110111001101101000"
		String b = "1"; // "1000011011000000111100110"
		System.out.println(addBinaryStringsv1(a, b));
		System.out.println(addBinaryStringsv2(a, b));
	}

	private static String addBinaryStringsv2(String A, String B) {
		int nA, nB;
		char res[];
		int i, j, k;
		nA = A.length();
		nB = B.length();
		res = new char[Math.max(nA, nB) + 1];
		k = Math.max(nA, nB);
		i = nA - 1;
		j = nB - 1;
		int sum = 0, carry = 0;
		// we add bits from the rightmost bit to the leftmost bit
		while (i >= 0 || j >= 0 || carry != 0) {
			sum = carry;
			if (i >= 0)
				sum += (A.charAt(i) - '0');
			if (j >= 0)
				sum += (B.charAt(j) - '0');
			res[k] = (char) ((sum % 2) + '0');
			carry = sum / 2;
			i--;
			j--;
			k--;
		}
		if (res[0] == '1')
			return new String(res);
		int len = Math.max(nA, nB);
		return new String(res, 1, len);
	}

	private static String addBinaryStringsv1(String num1, String num2) {
		int i = num1.length() - 1;
		int j = num2.length() - 1;
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		while (i >= 0 || j >= 0) {
			if (i >= 0) {
				sum += num1.charAt(i) - '0';
			}
			if (j >= 0) {
				sum += num2.charAt(j) - '0';
			}
			sb.insert(0, sum % 2);
			sum = sum / 2;
			i--;
			j--;
		}
		if (sum != 0) {
			sb.insert(0, 1);
		}
		return sb.toString();
	}

}
