package com.dsaproblems.DSAProblems.hashing02;

public class MakeStringPalindrome {

    public static void main(String[] args) {
        String A = "aacecaaaa";
        System.out.println(minCharsToMakePalindromev1(A));
        System.out.println(minCharsToMakePalindromev2(A));
        System.out.println(minCharsToMakePalindromev3(A));

        String x = "aa$aaaa";
        System.out.println(createLPS(x, x.length()));
    }

//    private static int minCharsToMakePalindromev3(String A) {
//        int n = A.length();
//        int longestPrefixLen = 0;
//        for (int i = n; i > 0; i--) {
//            String prefix = A.substring(0, i);
//            String suffix = A.substring(n - i);
//            if (prefix.equals(new StringBuilder(suffix).reverse().toString())) {
//                longestPrefixLen = i;
//                break;
//            }
//        }
//        return n - longestPrefixLen;
//    }

    private static int minCharsToMakePalindromev3(String A) {
        String reversed = new StringBuilder(A).reverse().toString();
        String longestPrefix = "";
        for (int i = A.length(); i > 0; i--) {
            String prefix1 = A.substring(0, i);
            String prefix2 = reversed.substring(0, i);
            if (prefix2.contains(prefix1)) {
                longestPrefix = prefix1;
                break;
            }
        }
        return A.length() - longestPrefix.length();
    }

    private static int minCharsToMakePalindromev2(String A) {
        StringBuilder s = new StringBuilder();
        s.append(A);

        String rev = s.reverse().toString();
        s.reverse().append("@").append(rev);

        int[] lps = createLPS(s.toString(), s.toString().length());

        return A.length() - lps[lps.length - 1];
    }

    private static int[] createLPS(String A, int N) {
        int[] lps = new int[N];
        lps[0] = 0;
        for (int i = 1; i < N; i++) {

            int x = lps[i - 1];

            while (A.charAt(i) != A.charAt(x)) {
                if (x == 0) {
                    x = -1;
                    break;
                }
                x = lps[x - 1];
            }

            lps[i] = x + 1;

        }

        return lps;
    }

    private static int minCharsToMakePalindromev1(String A) {
        StringBuilder s = new StringBuilder();
        s.append(A);

        String rev = s.reverse().toString();
        s.reverse();
        s.append("@").append(rev);

        // System.out.println(s.toString());

        int[] lps = createLPS(s.toString());

        int minCharToAdd = A.length() - lps[lps.length - 1];
        return minCharToAdd;
    }

    private static int[] createLPS(String p) {
        int[] lps = new int[p.length()];
        int i = 1;
        int len = 0;

        while (i < p.length()) {
            if (p.charAt(i) == p.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
        return lps;
    }
}
