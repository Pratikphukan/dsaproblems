package com.dsaproblems.DSAProblems.hashing02;

public class SmallestPrefixString {

    public static void main(String[] args) {
//        String A = "abba";
//        String B = "cdd";
//        String A = "a";
//        String B = "a";

//        String A = "tom";
//        String B = "riddle";

//        String A = "twvvsl";
//        String B = "wtcyawv";

        String A = "ababa";
        String B = "ahahahah";
        System.out.println(smallestPrefixStringv1(A, B));
        System.out.println(smallestPrefixStringv2(A, B));
        System.out.println(smallestPrefixStringv3(A, B));
        System.out.println(smallestPrefixStringv4(A, B));
    }

    //working code
    private static String smallestPrefixStringv4(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean isAppended = false;
        while (i < A.length()) {
            if (A.charAt(i) < B.charAt(0)) {
                sb.append(A.charAt(i));
                i++;
            } else {
                if (i == 0) {
                    sb.append(A.charAt(i));
                    i++;
                    continue;
                }
                sb.append(B.charAt(0));
                isAppended = true;
                break;
            }
        }
        if (!isAppended) sb.append(B.charAt(0));
        return sb.toString();
    }

    // this is the correct solution
    //Time Complexity: O(N), where N is the length of string A. The loop iterates at most once through A.
    //Space Complexity: O(N), for the StringBuilder used to build the result (in the worst case, the result is almost as long as A).
    private static String smallestPrefixStringv3(String A, String B) {
        StringBuilder sb = new StringBuilder();
        sb.append(A.charAt(0));
        int i = 1;
        // keep appending A[i] till it is smaller than B[0]
        while (i < A.length() && A.charAt(i) < B.charAt(0)) {
            sb.append(A.charAt(i));
            i++;
        }
        sb.append(B.charAt(0));
        return sb.toString();
    }

    // this is the correct solution
    private static String smallestPrefixStringv2(String A, String B) {
        StringBuilder sb = new StringBuilder();
        sb.append(A.charAt(0));
        for (int i = 1; i < A.length(); i++) {
            if (A.charAt(i) < B.charAt(0)) {
                sb.append(A.charAt(i));
            } else {
                break;
            }
        }
        sb.append(B.charAt(0));
        return sb.toString();
    }

    //not working
    private static String smallestPrefixStringv1(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < A.length() && j < B.length()) {
            if (A.charAt(i) < B.charAt(j)) {
                sb.append(A.charAt(i));
                i++;
            } else if (A.charAt(i) > B.charAt(j)) {
                sb.append(B.charAt(j));
                j++;
            } else {
                sb.append(A.charAt(i));
                i++;
                j++;
            }
        }
        if (i < A.length()) {
            sb.append(A.charAt(i));
        }
        if (j < B.length()) {
            sb.append(B.charAt(j));
        }
        return sb.toString();
    }
}
