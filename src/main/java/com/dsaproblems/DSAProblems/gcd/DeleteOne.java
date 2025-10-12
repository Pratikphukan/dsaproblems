package com.dsaproblems.DSAProblems.gcd;

import java.util.ArrayList;
import java.util.List;

/*
 * gcd(a,b,c)=gcd(gcd(a,b),c)=gcd(a,(b,c))=gcd((a.c),b)
 */
public class DeleteOne {

	public static void main(String[] args) {
		List<Integer> a = List.of(3, 9, 6, 8, 3);// (14, 17, 28, 70)
		ArrayList<Integer> A = new ArrayList<>(a);
		// (21, 7, 3, 42, 63), (3, 9, 6, 8, 3)
		System.out.println(MaxGCD(A));
	}

	private static int MaxGCD(ArrayList<Integer> A) {
		ArrayList<Integer> prefix = new ArrayList<Integer>();
		prefix.add(A.get(0));
		for (int i = 1; i < A.size(); i++) {
			prefix.add(GCD(prefix.get(i - 1), A.get(i)));
		}
		System.out.println(prefix);
		ArrayList<Integer> suffix = new ArrayList<Integer>();
		suffix.add(A.get(A.size() - 1));
		for (int i = A.size() - 2; i >= 0; i--) {
			suffix.add(0, GCD(suffix.get(0), A.get(i)));
		}
		System.out.println(suffix);
		// int ans = Math.max(prefix.get(1), suffix.get(A.size()-2)); //wrong approach
		int ans = Math.max(prefix.get(A.size() - 2), suffix.get(1));
		for (int i = 1; i < A.size() - 1; i++) {
			ans = Math.max(ans, GCD(prefix.get(i - 1), suffix.get(i + 1)));
		}
		return ans;

//		int n = A.size();
//		int Prefix[] = new int[n + 2];
//        Prefix[1] = A.get(0);
//        for (int i = 2; i <= n; i += 1)
//        {
//            Prefix[i] = GCD(Prefix[i - 1], A.get(i - 1));
//        }
//        for(int item : Prefix) {
//        	System.out.print(item+" ");
//        }
//        System.out.println();
//		int Suffix[] = new int[n + 2];
//		Suffix[n] = A.get(n - 1);
//		for (int i = n - 1; i >= 1; i -= 1) {
//			Suffix[i] = GCD(Suffix[i + 1], A.get(i - 1));
//		}
//		for (int item : Suffix) {
//			System.out.print(item + " ");
//		}
//		int ans = Math.max(Suffix[2], Prefix[n - 1]);
//		System.out.print("\n"+Suffix[2]+" "+Prefix[n-1]+" "+ans);
//		return 0;
	}

	private static Integer GCD(Integer a, Integer b) {
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}

}
