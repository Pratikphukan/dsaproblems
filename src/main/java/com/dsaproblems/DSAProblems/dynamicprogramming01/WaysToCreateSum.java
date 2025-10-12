package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaysToCreateSum {

    private final static Integer mod = 1000000007;

    public static void main(String[] args) {
        int n = 11;
        int ans = waysToCreate(n);
        ans = waysToCreate1(n);
        ans = waysToCreate2(n);
        System.out.println(waysToCreatev1(n));
        System.out.println(waysToCreatev2(n));
        System.out.println(waysToCreatev3(n));
        System.out.println(ans);

        ArrayList<Integer> dp = new ArrayList<>(Collections.nCopies(n + 1, -1));
        getWaysToSum(dp, n);
        System.out.println(dp.get(n) % mod);
    }

    private static int waysToCreatev3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1 + waysToCreatev3(0);
        }
        if (n == 2) {
            return 1 + waysToCreatev3(1) +
                    waysToCreatev3(0);
        }
        if (n == 3) {
            return 1 + waysToCreatev3(2) +
                    waysToCreatev3(1) +
                    waysToCreatev3(0);
        }
        if (n == 4) {
            return 1 + waysToCreatev3(3) +
                    waysToCreatev3(2) +
                    waysToCreatev3(1) +
                    waysToCreatev3(0);
        }
        if (n == 5) {
            return 1 + waysToCreatev3(4) +
                    waysToCreatev3(3) +
                    waysToCreatev3(2) +
                    waysToCreatev3(1) +
                    waysToCreatev3(0);
        }
        return 1 + waysToCreatev3(n - 1) +
                waysToCreatev3(n - 2) +
                waysToCreatev3(n - 3) +
                waysToCreatev3(n - 4) +
                waysToCreatev3(n - 5) +
                waysToCreatev3(n - 6);
    }

    //working code, throws TLE for large n
    private static int waysToCreatev2(int n) {
        if (n == 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= 6; i++) {
            if (n - i >= 0) {
                sum += waysToCreatev2(n - i);
            }
        }
        return sum;
    }

    //working code, throws TLE for large n
    private static int waysToCreatev1(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return waysToCreatev1(0);
        }
        if (n == 2) {
            return waysToCreatev1(1) +
                    waysToCreatev1(0);
        }
        if (n == 3) {
            return waysToCreatev1(2) +
                    waysToCreatev1(1) +
                    waysToCreatev1(0);
        }
        if (n == 4) {
            return waysToCreatev1(3) +
                    waysToCreatev1(2) +
                    waysToCreatev1(1) +
                    waysToCreatev1(0);
        }
        if (n == 5) {
            return waysToCreatev1(4) +
                    waysToCreatev1(3) +
                    waysToCreatev1(2) +
                    waysToCreatev1(1) +
                    waysToCreatev1(0);
        }
        return waysToCreatev1(n - 1) +
                waysToCreatev1(n - 2) +
                waysToCreatev1(n - 3) +
                waysToCreatev1(n - 4) +
                waysToCreatev1(n - 5) +
                waysToCreatev1(n - 6);
    }

    private static int waysToCreate2(int n) {
        List<Integer> prefix = new ArrayList<>();
        prefix.add(1);
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = (sum % mod + prefix.get(i - 1) % mod) % mod;
            if (i > 6) {
                sum -= prefix.get(i - 7);
            }
            prefix.add(sum);
        }
        return prefix.get(n) % mod;
    }

    private static int getWaysToSum(ArrayList<Integer> dp, int n) {
        if (dp.get(n) == -1) {
            if (n == 0 || n == 1) {
                dp.set(n, 1);
            }
            if (n == 2) {
                dp.set(n, n);
            }
            if (n > 2) {
                int val = 0;
                for (int i = 1; i <= 6 && n >= i; i++) {
                    val = (val % mod + getWaysToSum(dp, n - i) % mod) % mod;
                }
                dp.set(n, val);
            }
        }
        return dp.get(n) % mod;
    }

    private static int waysToCreate1(int n) {
        List<Integer> waysToCreateSum = new ArrayList<>();
        waysToCreateSum.add(1);
        int sum = waysToCreateSum.get(0);
        waysToCreateSum.add(sum);
        if (n < 2) {
            return waysToCreateSum.get(1);
        }
        sum += waysToCreateSum.get(1);
        waysToCreateSum.add(sum);
        if (n < 3) {
            return waysToCreateSum.get(2);
        }
        sum += waysToCreateSum.get(2);
        waysToCreateSum.add(sum);
        if (n < 4) {
            return waysToCreateSum.get(3);
        }
        sum += waysToCreateSum.get(3);
        waysToCreateSum.add(sum);
        if (n < 5) {
            return waysToCreateSum.get(4);
        }
        sum += waysToCreateSum.get(4);
        waysToCreateSum.add(sum);
        if (n < 6) {
            return waysToCreateSum.get(5);
        }
        sum += waysToCreateSum.get(5);
        waysToCreateSum.add(sum);
        if (n < 7) {
            return waysToCreateSum.get(6);
        }
        int len = waysToCreateSum.size() - 1;
        for (int i = len; i < n; i++) {
            sum += waysToCreateSum.get(i) - waysToCreateSum.get(i - 6);
            waysToCreateSum.add(sum);
        }
        return waysToCreateSum.get(n);
    }

    //working but throws TLE
    private static int waysToCreate(int n) {
        if (n == 0) {
            return 1;
        }
        int sum = 0;
        if (n > 0)
            sum += waysToCreate(n - 1);
        if (n > 1)
            sum += waysToCreate(n - 2);
        if (n > 2)
            sum += waysToCreate(n - 3);
        if (n > 3)
            sum += waysToCreate(n - 4);
        if (n > 4)
            sum += waysToCreate(n - 5);
        if (n > 5)
            sum += waysToCreate(n - 6);
        return sum;
    }

}
