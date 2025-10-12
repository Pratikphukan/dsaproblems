package com.dsaproblems.DSAProblems.searching;

public class AthMagicalNumber {

    public static void main(String[] args) {
        System.out.println(findAthMagicalNumber(4, 2, 3));
        System.out.println(findAthMagicalNumberv1(4, 2, 3));
        System.out.println(findAthMagicalNumberv2(4, 2, 3));
        System.out.println(findAthMagicalNumberv3(4, 2, 3));
    }

    //working code
    //mid/B counts numbers ≤ mid divisible by B.
    //mid/C counts numbers ≤ mid divisible by C.
    //Numbers divisible by both B and C (i.e., by LCM(B, C)) are counted in both, so we subtract mid/LCM to get the correct total count of numbers ≤ mid divisible by B or C.
    //This is the inclusion-exclusion principle.
    private static int findAthMagicalNumberv3(int A, int B, int C) {
        long mod = 1_000_000_007;
        long lcm = (long) B * C / gcd(B, C);
        long low = 1, high = (long) A * Math.min(B, C);

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = mid / B + mid / C - mid / lcm;
            if (count < A) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int) (low % mod);
    }

    //If the second number (C) is 0, the GCD is the first number (B)
    //recursively call gcd(C, B % C) until the second number becomes 0
    //the GCD of two numbers also divides their difference
    //recursion continues until the remainder is 0, at which point the other number is the GCD
    public static int gcd(int B, int C) {
        if (C == 0) {
            return B;
        }
        return gcd(C, B % C);
    }

    //working code
    public static int findAthMagicalNumberv2(int A, int B, int C) {
        long start = Math.min(B, C);
        long end = start * A;
        long lcm = ((long) B * C) / (gcd(B, C));

        while (start <= end) {
            long mid = (start + end) / 2;
            if (mid % B == 0 || mid % C == 0)
                if (((mid / B) + (mid / C) - (mid / lcm)) == A) {
                    return (int) (mid % (Math.pow(10, 9) + 7));
                } else if (((mid / B) + (mid / C) - (mid / lcm)) < A) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            else {
                if (((mid / B) + (mid / C) - (mid / lcm)) == A) {
                    return (int) ((mid - Math.min(mid % B, mid % C)) % (Math.pow(10, 9) + 7));
                } else if (((mid / B) + (mid / C) - (mid / lcm)) < A) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return 0;
    }

    private static int findAthMagicalNumberv1(int A, int B, int C) {
        long mod = 1000000007;
        long l = Math.min(B, C);
        long h = (long) A * Math.min(B, C);
        long ans = h;
        while (l <= h) {
            long mid = (l + h) / 2;
            long x = count(B, C, mid);
            if (x >= A) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        ans = ans % mod;
        return (int) ans;
    }

    private static long count(int B, int C, long mid) {
        long prod = B * C;
        long mod = 1000000007;
        prod = prod % mod;
        return mid / B + mid / C - mid / (prod / GCD(B, C));
    }

    private static int findAthMagicalNumber(int a, int b, int c) {
        int lcm = (b * c) / GCD(b, c);
        long left = 0;
        long right = a * lcm;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if ((mid / b + mid / c - mid / lcm) < a) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int) left % 1000000007;
    }

    private static int GCD(int b, int c) {
        if (c == 0) {
            return b;
        }
        return GCD(c, b % c);
    }
}
