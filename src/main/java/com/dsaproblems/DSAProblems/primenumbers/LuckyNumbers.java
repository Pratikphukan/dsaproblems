package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;
import java.util.HashSet;

public class LuckyNumbers {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(luckyNumbersv1(12));
        System.out.println(luckyNumbersv2(21));
    }

    private static int luckyNumbersv2(int n) {
        if (n < 6) return 0;
        int[] spf = smallestPrimeFactorsv2(n);
        int[] distinctCount = new int[n + 1];
        distinctCount[1] = 0;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            int p = spf[i];
            int m = i / p;
            // if same prime factor continues, no new distinct prime; otherwise add one
            distinctCount[i] = distinctCount[m] + (p == spf[m] ? 0 : 1);
            if (distinctCount[i] == 2) result++;
        }
        return result;
    }

    private static int[] smallestPrimeFactorsv2(int n) {
        int[] spf = new int[n + 1];
        for (int i = 0; i <= n; i++) spf[i] = i;
        for (int i = 2; i * i <= n; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= n; j += i) {
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }
        return spf;
    }

    private static ArrayList<Integer> smallestPrimeFactorsv1(int n) {
//		isPrime[0] = isPrime[1] = false;
//        for (int i = 2; i <= n; i++)
//            isPrime[i] = true;
//     
//        for (int p = 2; p * p <= n; p++){
//            if (isPrime[p] == true){
//                for (int i = p * p; i <= n; i += p)
//                    isPrime[i] = false;
//            }
//        }
        ArrayList<Integer> spf = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            spf.add(i);
        }
        for (int i = 2; i * i <= n; i++) {
            if (spf.get(i) == i) { // this means i is prime
                for (int j = i * i; j <= n; j = j + i) {
                    if (spf.get(j) == j) {
                        spf.set(j, i);
                    }
                }
            }

        }
        System.out.println(spf);
        return spf;
    }

    private static int luckyNumbersv1(int n) {
        ArrayList<Integer> spf = smallestPrimeFactorsv1(n);
        int count = 0;
        for (int i = 6; i <= n; i++) {
            HashSet<Integer> set = new HashSet<>();
            int num = i;
            while (num > 1) {
                int x = spf.get(num);
                set.add(x);
                num = num / x;
            }
            if (set.size() == 2) {
                count++;
            }
        }
        return count;
    }
}
