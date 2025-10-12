package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Directories {

    //Input ["/a","/a/b","/c/d","/c/d/e","/c/f"]
    //Output ["/a","/c/d","/c/f"]

    //Input  ["/a/b/c","/a/b/ca","/a/b/d"]
    //Output ["/a/b/c","/a/b/ca","/a/b/d"]

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("/a/b/c", "/a/b/ca", "/a/b/d"));
        System.out.println(minimumDirectoryCreationv1(input));
        input = new ArrayList<>(Arrays.asList("/a/b/c", "/a/b/ca", "/a/b/d"));
        System.out.println(minimumDirectoryCreationv2(input));
        System.out.println(solve(4L, 4L, 3L, 2L, 1L));
        System.out.println(solve(2L, 2L, 2L, 4L, 4L));
    }

    //        int time = 0;
//        int inA = 0, outA = 0;
//        int inB = 0, outB = 0;
//        int inC = 0, outC = 0;
//        int inD = 0, outD = 0;
//        if (N <= A) {
//            inA = N;
//        } else {
//            inA = A;
//            outA = N - A;
//        }
//        time += 1;
//        if (inA <= B) {
//            inB = inA;
//        } else {
//            inB = B;
//            outB = inA - B;
//        }
//        time += 1;
//        if (inB <= C) {
//            inC = inB;
//        } else {
//            inC = C;
//            outC = inB - C;
//        }
//        time += 1;
//        if (inC <= D) {
//            inD = inC;
//        } else {
//            inD = D;
//            outD = inC - D;
//        }
//        time += 1;
    private static long solve(long N, long A, long B, long C, long D) {
//        int time = 0;
//        int inA = 0, outA = N;
//        int inB = 0, outB = 0;
//        int inC = 0, outC = 0;
//        int inD = 0, outD = 0;
//        while (outA != 0 || outB != 0 || outC != 0 || outD != 0) {
//            if (outA > 0) {
//                if (outA <= A) {
//                    inA = outA;
//                } else {
//                    inA = A;
//                }
//                outA = outA - inA;
//                time += 1;
//                outB = inA;
//            }
//            if (outB > 0) {
//                if (outB <= B) {
//                    inB = outB;
//                } else {
//                    inB = B;
//                }
//                outB = outB - inB;
//                time += 1;
//                outC = inB;
//            }
//            if (outC > 0) {
//                if (outC <= C) {
//                    inC = outC;
//                } else {
//                    inC = C;
//                }
//                outC = outC - inC;
//                time += 1;
//                outD = inC;
//            }
//            if (outD > 0) {
//                if (outD <= D) {
//                    inD = outD;
//                } else {
//                    inD = D;
//                }
//                outD = outD - inD;
//                time += 1;
//            }
//        }
//        return time;

        long q1 = N, q2 = 0, q3 = 0, q4 = 0, reached = 0;
        long t = 0;
        while (reached < N) {
            long m4 = Math.min(D, q4);
            q4 -= m4;
            reached += m4;   // S4->S5
            long m3 = Math.min(C, q3);
            q3 -= m3;
            q4 += m3;        // S3->S4
            long m2 = Math.min(B, q2);
            q2 -= m2;
            q3 += m2;        // S2->S3
            long m1 = Math.min(A, q1);
            q1 -= m1;
            q2 += m1;        // S1->S2
            t++;                                                  // one minute passed
        }
        return t;
    }

    private static List<String> minimumDirectoryCreationv2(List<String> input) {
        Collections.sort(input);
        List<String> result = new ArrayList<>();
        for (String path : input) {
            if (result.isEmpty() || !path.startsWith(result.get(result.size() - 1) + "/")) {
                result.add(path);
            }
        }
        return result;
    }

    private static List<String> minimumDirectoryCreationv1(List<String> input) {
        Collections.sort(input);
        input.replaceAll(s -> {
            return s + "/"; // Append '/' to each path
        });
        List<String> result = new ArrayList<>();
        String prefix = "";
        for (String s : input) {
            if (result.isEmpty()) {
                result.add(s.substring(0, s.length() - 1));
                prefix = s;
            } else {
                if (prefix.length() <= s.length() && s.substring(0, prefix.length()).equals(prefix)) {
                    continue;
                } else {
                    result.add(s.substring(0, s.length() - 1));
                    prefix = s;
                }
            }
        }
        return result;
    }
}
