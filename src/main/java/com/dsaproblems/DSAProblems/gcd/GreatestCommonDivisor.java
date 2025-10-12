package com.dsaproblems.DSAProblems.gcd;

/*
 * properties of gcd:
 * gcd(a,b)=gcd(|a|,|b|)
 * gcd(0,x)=|x|, gcd(0,-7)=7
 * gcd(0,0)->infinity or undefined
 * gcd(10,-25)=5, 10->1,2,5,10; -25->-25,-5,-1,1,5,25
 * gcd(a,b)=x, a>b, a%x=0,b%x=0
 * gcb(a-b, b)=x, (a-b)%x=0, b%x=0
 * (a-b)%x=0 => (a%x -b%x +x)%x=0 => (0-0+x)%x=0
 */
public class GreatestCommonDivisor {

	public static void main(String[] args) {
		// (3,9),(2,2),(69,23),(350,136), (48,816), (1000000000, 1000000)
		System.out.println(gcdv1(-15, -7));
		System.out.println(gcdv2(16, 16));
		System.out.println(gcdv3(16, 16));
		System.out.println(gcdv4(1000000000, 1000000));
	}

	// working code
	private static int gcdv4(int A, int B) {
		int a = Math.abs(A);
		int b = Math.abs(B);
		int largerNum = Math.max(a, b);
		int smallerNum = Math.min(a, b);
		if (a == 0 || b == 0) {
			return largerNum;
		}
		if (a == b) {
			return a;
		}
		int ans = 1;
		for (int i = 1; i <= Math.sqrt(smallerNum); i++) {
			if (smallerNum % i == 0) {
				if (smallerNum == i * i) {
					if (largerNum % i == 0) {
						ans = Math.max(ans, i);
					}
				} else {
					if (largerNum % i == 0) {
						ans = Math.max(ans, i);
					}
					if (largerNum % (smallerNum / i) == 0) {
						ans = Math.max(ans, smallerNum / i);
					}
				}
			}
		}
		return ans;
	}

	// working code
	private static int gcdv3(int A, int B) {
		int a = Math.min(Math.abs(A), Math.abs(B));
		int b = Math.max(Math.abs(A), Math.abs(B));
		return (a == 0) ? b : gcdv3(b - a, a);
	}

	// working code, but throwing TLE
	// TC: O(min(a,b))
	public static int gcdv1(int A, int B) {
		A = Math.abs(A);
		B = Math.abs(B);
		if (A == 0) {
			return B;
		}
		if (B == 0) {
			return A;
		}
		int i = Math.min(A, B);
		for (; i >= 1; i--) {
			if (A % i == 0 && B % i == 0) {
				return i;
			}
		}
		return i + 1;
	}

	public static int gcdv2(int A, int B) {
		if (A < B) {
			int temp = A;
			A = B;
			B = temp;
		}
		// System.out.printf("%d, %d\n", A, B);
		if (B == 0) {
			return A;
		}
		return gcdv2(A - B, B);
	}

}
