package com.dsaproblems.DSAProblems.gcd;

public class Program1 {

	public static void main(String[] args) {
		// GCD(15,25)=5, greatest factor which divides both 15 and 25
		// GCD(a,b)=x, a%x=0, b%x=0 and x should be the highest factor
		// GCD(12,30)=6
		// GCD(10,-25)=5, positive numbers can also divide -25
		// GCD(10,-25)=GCD(10,25)
		// GCD(0,8)=8, any positive number can divide 0
		// GCD(0,a)=a
		// GCD(0,-10)= 10
		// GCD(0,-a)=a
		// GCD(-16,-24)=GCD(16,24)
		// -16->-16,-8,-4,-2,-1,1,2,4,8,16
		// -10->-10,-5,-2,-1,1,2,5,10
		// for negative you're not required to take the negative into consideration
		// GCD(-2,-3)=GCD(2,3)=1
		// GCD(a,b)=GCD(|a|,|b|)
		// GCD(0,x)=GCD(0,|x|)=|x|
		// GCD(0,0) is infinite
		// GCD(a,b,c)=GCD((a,b),c)=GCD((a,c),b)=GCD((b,c),a)
		System.out.println(GCD(15, 25));
		System.out.println(GCD(30, 15));
		System.out.println(GCD(2, 3));
		System.out.println(GCD1(23, 45));
		System.out.println(GCD1(30, 15));
		System.out.println(GCD1(8, 6));
		// a,b>0, gcd(a,b)=gcd(a-b,b)=x
		// a%x=b%x=0, b%x=0,(a-b)%x=(a%x-b%x+x)%x=(0-0+x)%x=0
		// gcd(a,b)=gcd(a-b,b) if a>b**
		// gcd(a,b)=gcd(b,a-b)=gcd(b,a-b-b)=gcd(b,a-kb)
		// gcd(a,b)=gcd(b,a%b), a>b***, b>a%b
		// the code will itself reverse the values**
		System.out.println(GCD3(30, 15));
		System.out.println(GCD3(8, 6));
	}

	private static int GCD3(int a, int b) {
		if (b == 0) {
			return a;
		}
		return GCD3(b, a % b);
	}

//	private static int GCD2(int i, int j) {
//		// TODO Auto-generated method stub
//		return 1;
//	}

	private static int GCD1(int a, int b) {
//		if(a==0||b==0) {
//			return Math.max(a, b);
//		}
		if (b == 0) {
			return a;
		}
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		return GCD1(a - b, b);
	}

	private static int GCD(int a, int b) {
		// GCD cannot exceed the min(a,b)
		// max possible GCD is min(a,b)
		// TC->O(min(a,b)),SC->O(1)
		if (a == 0 || b == 0) {
			return Math.max(a, b);
		}
		int min = a;
		if (a > b) {
			min = b;
		}
		for (int i = min; i > 1; i--) { // a=10,b=0
			if (a % i == 0 && b % i == 0) {
				return i;
			}
		}
		return 1;
	}

}
