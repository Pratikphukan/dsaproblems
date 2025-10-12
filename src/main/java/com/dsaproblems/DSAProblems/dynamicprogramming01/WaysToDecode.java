package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.List;

public class WaysToDecode {

    public static void main(String[] args) {
        //String A = "101";
        //String A = "121";
        String A = "1111";
        StringBuilder sb = new StringBuilder(A);
        System.out.println(sb.substring(0, 0));
        //System.out.println(totalWaysToDecode(sb));
        System.out.println(totalWaysToDecodev2(A, A.length()));
        System.out.println(totalWaysToDecodev3(A, A.length()));

        System.out.println(totalWaysToDecodev1("2611055971756562"));
        System.out.println(totalWaysToDecodev4("2611055971756562"));
    }

    //working code
    private static int totalWaysToDecodev4(String A) {
        List<Integer> dp = new ArrayList<>();
        if (Integer.parseInt(A.substring(0, 1)) == 0) {
            return 0;
        }
        dp.add(1);
        if (A.length() > 1) {
            if (Integer.parseInt(A.substring(1, 2)) > 0)
                dp.add(1);
            else
                dp.add(0);
        } else {
            return 1;
        }
        for (int i = 2; i <= A.length(); i++) {
            int unitDigitNumber = Integer.parseInt(A.substring(i - 1, i));
            int oneDigitDecodingWays = 0;
            if (unitDigitNumber > 0) {
                oneDigitDecodingWays = dp.get(i - 1);
            }
            int twoDigitNumber = Integer.parseInt(A.substring(i - 2, i));
            int twoDigitsDecodingWays = 0;
            if (twoDigitNumber >= 10 && twoDigitNumber <= 26) {
                twoDigitsDecodingWays = dp.get(i - 2);
            }
            dp.add((oneDigitDecodingWays % 1000000007 + twoDigitsDecodingWays % 1000000000) % 1000000007);
        }
        return dp.get(A.length());
    }

    //not working, need to fix
    private static int totalWaysToDecodev3(String A, int lenA) {
        int[] dp = new int[lenA + 1];
        return totalWaysToDecodev3(A, lenA, dp);
    }

    private static int totalWaysToDecodev3(String A, int lenA, int[] dp) {
        if (dp[lenA] == 0) {
            if (lenA == 1 && A.charAt(0) != '0') {
                dp[lenA] += 1;
            }
            if (lenA > 1 && A.charAt(lenA - 1) != '0') {
                dp[lenA] += totalWaysToDecodev3(A, lenA - 1, dp);
            }
            if (lenA == 2 && (
                    (A.charAt(0) == '1' && A.charAt(1) >= '0' && A.charAt(1) <= '9') ||
                            (A.charAt(0) == '2' && A.charAt(1) >= '0' && A.charAt(1) <= '6'))) {
                dp[lenA] += 1;
            }
            if (lenA > 2 && (
                    (A.charAt(lenA - 2) == '1' && A.charAt(lenA - 1) >= '0' && A.charAt(lenA - 1) <= '9') ||
                            (A.charAt(lenA - 2) == '2' && A.charAt(lenA - 1) >= '0' && A.charAt(lenA - 1) <= '6'))) {
                dp[lenA] += totalWaysToDecodev3(A, lenA - 2, dp);
            }
        }
        return dp[lenA];
    }

    //working but throws TLE
    private static int totalWaysToDecodev2(String A, int lenA) {
        int totalWays = 0;
        if (lenA == 1 && A.charAt(0) != '0') {
            totalWays += 1;
        }
        if (lenA > 1 && A.charAt(lenA - 1) != '0') {
            totalWays += totalWaysToDecodev2(A, lenA - 1);
        }
        if (lenA == 2 && (
                (A.charAt(0) == '1' && A.charAt(1) >= '0' && A.charAt(1) <= '9') ||
                        (A.charAt(0) == '2' && A.charAt(1) >= '0' && A.charAt(1) <= '6'))) {
            totalWays += 1;
        }
        if (lenA > 2 && (
                (A.charAt(lenA - 2) == '1' && A.charAt(lenA - 1) >= '0' && A.charAt(lenA - 1) <= '9') ||
                        (A.charAt(lenA - 2) == '2' && A.charAt(lenA - 1) >= '0' && A.charAt(lenA - 1) <= '6'))) {
            totalWays += totalWaysToDecodev2(A, lenA - 2);
        }
        return totalWays;
    }

    //working code
    private static int totalWaysToDecodev1(String A) {
//        List<Integer> digits = new ArrayList<>();
//        for (Character c : A.toCharArray()) {
//            digits.add(c - '0');
//        }
//        if (digits.get(0) == 0) {
//            return 0;
//        }
//        List<Integer> dp = new ArrayList<>();
//        for (int i = 0; i <= A.length(); i++) {
//            dp.add(0);
//        }
//        dp.set(0, 1);
//        dp.set(1, 1);
//        int twoDigitNumber = 0;
//        for (int i = 2; i <= A.length(); i++) {
//            twoDigitNumber = digits.get(i - 2) * 10 + digits.get(i - 1);
//            if (twoDigitNumber > 9 && twoDigitNumber < 27) {
//                if (digits.get(i - 1) == 0) {
//                    dp.set(i, dp.get(i - 1));
//                } else if (digits.get(i - 2) == 0) {
//                    dp.set(i, dp.get(i - 2));
//                } else {
//                    dp.set(i, dp.get(i - 1) + dp.get(i - 2));
//                }
//            } else {
//                dp.set(i, dp.get(i - 1));
//            }
//        }
//        return dp.get(A.length());

        int n = A.length();
        if (n == 0 || A.charAt(0) == '0') return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            char one = A.charAt(i - 1);
            char two = A.charAt(i - 2);
            if (one != '0')
                dp[i] = (dp[i] % 1000000007 + dp[i - 1] % 1000000007) % 1000000007;
            if (two == '1' || (two == '2' && one >= '0' && one <= '6'))
                dp[i] = (dp[i] % 1000000007 + dp[i - 2] % 1000000007) % 1000000007;
        }
        return dp[n];
    }

    private static Integer totalWaysToDecode(StringBuilder sb) {
        int len = sb.length();
        int val = Character.getNumericValue(sb.charAt(len - 1));
        int ans = 0;
        if (len == 1) {
            if (val > 0 && Character.getNumericValue(sb.charAt(len)) != 0) {
                ans += 1;
            }
            return ans;
        }
        if (len == 2) {
            if (val > 0) {
                ans += 1;
            }
            val = Integer.parseInt(sb.substring(0, len));
            if (val < 26) {
                ans += 1;
            }
            return ans;
        }
        return totalWaysToDecode(new StringBuilder(sb.substring(0, sb.length() - 1)))
                + totalWaysToDecode(new StringBuilder(sb.substring(0, sb.length() - 2)));
    }
}
