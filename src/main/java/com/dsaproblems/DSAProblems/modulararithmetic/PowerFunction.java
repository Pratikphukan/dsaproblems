package com.dsaproblems.DSAProblems.modulararithmetic;

public class PowerFunction {

	public static void main(String[] args) {
		System.out.println(pow(213, 231, 1));
		System.out.println(pow1(213, 231, 1));
		System.out.println(pow2(213, 231, 1));
	}

	// A : 73887417, B : 88716815, C : 18305953

	private static long pow2(int x, int n, int p) {
		if (n == 0)
			return 1 % p;
		long ans = 1, base = x;
		while (n > 0) {
			// We need (base ** n) % p.
			// Now there are 2 cases.
			// 1) n is even. Then we can make base = base^2 and n = n / 2.
			// 2) n is odd. So we need base * base^(n-1)
			if (n % 2 == 1) {
				ans = (ans * base) % p;
				n--;
			} else {
				base = (base * base) % p;
				n /= 2;
			}
		}
		if (ans < 0)
			ans = (ans + p) % p;
		return ans;
	}

	private static int pow1(int x, int n, int d) {
		long a = x;
		long res = 1L;
		while (n > 0) {
			if (n % 2 == 1) {
				res *= a;
				res %= d;
			}
			a *= a;
			a %= d;
			n = n >> 1;
		}
		res = (res + d) % d;
		return (int) res;
	}

	// working code
	private static long pow(int A, int B, int C) {
		if (A == 0) {
			return 0;
		}
		if (B == 0) {
			return 1;
		}
		long y = pow(A, B / 2, C);
		if (B % 2 == 0) {
			return (y * y) % C;
		}
		if (A < 0) {
			return ((y * y) % C * A) % C + C;
		} else {
			return ((y * y) % C * A) % C;
		}
	}
}
