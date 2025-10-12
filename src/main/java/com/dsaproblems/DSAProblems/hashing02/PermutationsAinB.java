package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashMap;
import java.util.Map;

public class PermutationsAinB {

    public static void main(String[] args) {
        //abc|abcbacabc
        String A = "ebbp";
        String B = "qaoedpcebeaqocbacoccqoebpqdoqcpbdbqcecdoqcpebqpebbabqdpepcpbqbepbabocpc";

        //ebbp
        //qaoedpcebeaqocbacoccqoebpqdoqcpbdbqcecdoqcpebqpebbabqdpepcpbqbepbabocpc

        System.out.println(findPermutationsAinBv1(A, B));
        System.out.println(findPermutationsAinBv2(A, B));
    }

    //working and optimized
    private static int findPermutationsAinBv2(String A, String B) {
        int lenA = A.length(), lenB = B.length();
        if (lenA > lenB) return 0;
        int count = 0;
        Map<Character, Integer> freqA = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : A.toCharArray())
            freqA.put(c, freqA.getOrDefault(c, 0) + 1);
        for (int i = 0; i < lenA; i++)
            window.put(B.charAt(i), window.getOrDefault(B.charAt(i), 0) + 1);
        if (window.equals(freqA)) count++;
        for (int i = lenA; i < lenB; i++) {
            char out = B.charAt(i - lenA), in = B.charAt(i);
            window.put(out, window.get(out) - 1);
            if (window.get(out) == 0) window.remove(out);
            window.put(in, window.getOrDefault(in, 0) + 1);
            if (window.equals(freqA)) count++;
        }
        return count;
    }

    //working but throws TLE
    private static int findPermutationsAinBv1(String A, String B) {
        int lenA = A.length();
        int count = 0;
        Map<Character, Integer> freqA = new HashMap<>();
        for (char c : A.toCharArray()) {
            freqA.put(c, freqA.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i <= B.length() - lenA; i++) {
            Map<Character, Integer> windowFreq = new HashMap<>(freqA);
            boolean isPermutation = true;
            for (int j = i; j < i + lenA; j++) {
                char ch = B.charAt(j);
                if (!windowFreq.containsKey(ch)) {
                    isPermutation = false;
                    break;
                }
                windowFreq.put(ch, windowFreq.get(ch) - 1);
                if (windowFreq.get(ch) == 0) windowFreq.remove(ch);
            }
            if (windowFreq.isEmpty() && isPermutation) count++;
        }
        return count;
    }
}
