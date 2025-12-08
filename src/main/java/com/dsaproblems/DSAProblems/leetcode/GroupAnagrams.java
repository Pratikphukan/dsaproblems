package com.dsaproblems.DSAProblems.leetcode;

import java.util.*;

public class GroupAnagrams {

    static class Word implements Comparable<Word> {
        String val;
        int[] charCount;

        Word(String val) {
            this.val = val;
            this.charCount = new int[26];
            for (char c : val.toCharArray()) {
                charCount[c - 'a']++;
            }
        }

        @Override
        public int compareTo(Word other) {
            for (int i = 0; i < 26; i++) {
                if (this.charCount[i] != other.charCount[i]) {
                    return Integer.compare(this.charCount[i], other.charCount[i]);
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            return this.val;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Word word = (Word) o;
            return Objects.deepEquals(charCount, word.charCount);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(charCount);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagramsTogetherv1(arr));
        System.out.println(groupAnagramsTogetherv2(arr));
        System.out.println(groupAnagramsTogetherv3(arr));

        String[] a1 = arr.clone();
        Arrays.sort(a1);
        System.out.println(Arrays.toString(a1));

        char[] a = {'a', 'b', 'c'};
        char[] b = {'a', 'b', 'c'};
        char[] c = {'A', 'B', 'C'};

        //Returns true if the two specified arrays of booleans are equal to one another.
        // Two arrays are considered equal if both arrays contain the same number of elements,
        // and all corresponding pairs of elements in the two arrays are equal.
        // In other words, two arrays are equal if they contain the same elements in
        // the same order. Also, two array references are considered equal if both are null
        // content equality
        System.out.println(Arrays.equals(a, b)); // true
        // different case -> false
        System.out.println(Arrays.equals(a, c)); // false
    }

    private static List<List<String>> groupAnagramsTogetherv3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    //HashMap uses an object's hashCode() to pick a bucket and equals() to find the exact key inside that bucket.
    //Contract: if a.equals(b) is true then a.hashCode() == b.hashCode() must hold. Violating this breaks map lookup.
    //Implement both equals and hashCode together, prefer immutable key fields, and avoid using mutable fields that affect equality/hash.
    //If hashCode is poor (many collisions) performance suffers; if inconsistent with equals lookups fail.
    private static List<List<String>> groupAnagramsTogetherv2(String[] strs) {
        Map<Word, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Word word = new Word(str);
            if (!map.containsKey(word)) map.put(word, new ArrayList<>(List.of(str)));
            else map.get(word).add(str);
        }
        return new ArrayList<>(map.values());
    }

    //working code
    private static List<List<String>> groupAnagramsTogetherv1(String[] strs) {
        int len = strs.length;
        List<Word> words = new ArrayList<>();
        for (String str : strs) {
            words.add(new Word(str));
        }
        Collections.sort(words);
        List<Integer> ends = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            if (words.get(i).compareTo(words.get(i - 1)) != 0) ends.add(i - 1);
        }
        List<List<String>> result = new ArrayList<>();
        int i = 0;
        for (int end : ends) {
            List<String> part = new ArrayList<>();
            for (; i <= end; i++) {
                part.add(words.get(i).val);
            }
            result.add(part);
        }
        if (i < len) {
            List<String> part = new ArrayList<>();
            for (; i < len; i++) {
                part.add(words.get(i).val);
            }
            result.add(part);
        }
        return result;
    }
}
