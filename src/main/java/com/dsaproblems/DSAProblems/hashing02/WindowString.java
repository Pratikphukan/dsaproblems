package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashMap;
import java.util.Map;

public class WindowString {

    public static void main(String[] args) {
//		String a = "ADOBECODEBANC";
//		String b = "ABC";

        String a = "timetopractice";
        String b = "toc";
        // findWindowWithMinLengthv4(a, b);
        System.out.println(findWindowWithMinLengthv5(a, b));
        System.out.println(findWindowWithMinLengthv6(a, b));
        System.out.println(findSubString(a, b));
        System.out.println(findSubStringv1(a, b));
        System.out.println(findSubStringv2(a, b));
        System.out.println(findSubStringv3(a, b));
    }

    //working code
    private static String findSubStringv3(String A, String B) {
        if (A.length() < B.length())
            return "";
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            counts.put(B.charAt(i), counts.getOrDefault(B.charAt(i), 0) + 1);
        }
        int start = 0;
        int length = 0;
        int total = 0;
        for (int head = 0, tail = 0; tail < A.length(); tail++) {
            if (!counts.containsKey(A.charAt(tail))) {
                continue;
            }
            counts.put(A.charAt(tail), counts.get(A.charAt(tail)) - 1);
            if (counts.get(A.charAt(tail)) >= 0) {
                total++;
            }
            if (total == B.length()) {
                while (counts.get(A.charAt(head)) == null || counts.get(A.charAt(head)) < 0) {
                    if (counts.get(A.charAt(head)) != null)
                        counts.put(A.charAt(head), counts.get(A.charAt(head)) + 1);
                    head++;
                }
                if (length == 0 || tail - head + 1 < length) {
                    length = tail - head + 1;
                    start = head;
                }
            }
        }
        return A.substring(start, start + length);
    }

    private static String findSubStringv2(String A, String B) {
        if (A.length() < B.length())
            return "";
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            if (counts.get(B.charAt(i)) == null) {
                counts.put(B.charAt(i), 1);
            } else {
                counts.put(B.charAt(i), counts.get(B.charAt(i)) + 1);
            }
        }
        int start = 0;
        int length = 0;
        int total = 0;
        for (int head = 0, tail = 0; tail < A.length(); tail++) {
            if (counts.get(A.charAt(tail)) == null) {
                // If this character is not present in B at all,
                // we don't care about this character.
                continue;
            }
            counts.put(A.charAt(tail), counts.get(A.charAt(tail)) - 1);
            // We check if the current character represented by tail caused
            // a character to be included which is relevant to B and is still
            // in deficit.
            // For example, if B has 3 As, then the first 3 A are relevant to us
            // but the 4th one is not.
            if (counts.get(A.charAt(tail)) >= 0) {
                total++;
            }
            // check if we have all characters in B covered.
            if (total == B.length()) {
                // Now move the head pointer till popping out those characters
                // still makes sure that all characters in B are covered.
                while (counts.get(A.charAt(head)) == null || counts.get(A.charAt(head)) < 0) {
                    if (counts.get(A.charAt(head)) != null)
                        counts.put(A.charAt(head), counts.get(A.charAt(head)) + 1);
                    head++;
                }
                // Now [head - 1, tail] is a valid substring. Update the answer.
                if (length == 0 || tail - head + 1 < length) {
                    length = tail - head + 1;
                    start = head;
                }
            }
        }
        return A.substring(start, start + length);

    }

    //working code and better
    //TC: O(N), where N is the length of string A. Each character is processed at most twice (once by tail, once by head)
    //SC: O(M), where M is the number of unique characters in string B (stored in the counts map)
    private static String findSubStringv1(String A, String B) {
        if (A.length() < B.length()) return "";
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            counts.put(B.charAt(i), counts.getOrDefault(B.charAt(i), 0) + 1);
        }
        int start = 0, length = 0, total = 0;
        for (int head = 0, tail = 0; tail < A.length(); tail++) {
            char t = A.charAt(tail);
            if (!counts.containsKey(t)) {
                continue;
            }
            counts.put(t, counts.get(t) - 1);
            if (counts.get(t) >= 0) {
                total++;
            }
            if (total == B.length()) {
                char h = A.charAt(head);
                while (!counts.containsKey(h) || counts.get(h) < 0) {
                    if (counts.containsKey(h))
                        counts.put(h, counts.get(h) + 1);
                    h = A.charAt(++head);
                }
                if (length == 0 || tail - head + 1 < length) {
                    length = tail - head + 1;
                    start = head;
                }
            }
        }
        return A.substring(start, start + length);
    }

    private static String findWindowWithMinLengthv6(String input, String pattern) {
        int len1 = input.length();
        int len2 = pattern.length();
        if (len1 < len2) {
            return "";
        }
        Map<Character, Integer> hash_input = new HashMap<>();
        Map<Character, Integer> hash_pattern = new HashMap<>();
        for (int i = 0; i < len2; i++)
            hash_pattern.put(pattern.charAt(i), hash_pattern.getOrDefault(pattern.charAt(i), 0) + 1);
        int count = 0;
        int start_idx = -1;
        int start = 0;
        int min_len = Integer.MAX_VALUE;
        Character curr = null;
        Character initial = null;
        for (int j = 0; j < len1; j++) {
            curr = input.charAt(j);
            hash_input.put(curr, hash_input.getOrDefault(curr, 0) + 1);
            if (hash_pattern.containsKey(curr) && hash_input.get(curr) <= hash_pattern.get(curr)) {
                count++;
            }
            if (count == len2) {
                initial = input.charAt(start);
                while (!hash_pattern.containsKey(initial)
                        || hash_pattern.containsKey(initial) && hash_input.get(initial) > hash_pattern.get(initial)) {
                    if (hash_pattern.containsKey(initial) && hash_input.get(initial) > hash_pattern.get(initial)) {
                        hash_input.put(initial, hash_input.get(initial) - 1);
                    }
                    start++;
                    initial = input.charAt(start);
                }
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_idx = start;
                }
            }
        }
        if (start_idx == -1) {
            return "";
        }
        return input.substring(start_idx, start_idx + min_len);
    }

    //working code and optimal
    // optimal in time (O(N)) and space (O(1))
    private static String findWindowWithMinLengthv5(String input, String pattern) {
        int len1 = input.length(), len2 = pattern.length();
        if (len1 < len2) {
            return "";
        }
        int[] hash_pat = new int[256];
        int[] hash_str = new int[256];
        int start = 0, startIdx = -1, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < len2; i++)
            hash_pat[pattern.charAt(i)]++;
        int count = 0;
        for (int end = 0; end < len1; end++) {
            char c = input.charAt(end);
            hash_str[c]++;
            if (hash_str[c] <= hash_pat[c])
                count++;
            if (count == len2) {
                char sc = input.charAt(start);
                while (hash_str[sc] > hash_pat[sc]
                        || hash_pat[sc] == 0) {
                    if (hash_str[sc] > hash_pat[sc])
                        hash_str[sc]--;
                    sc = input.charAt(++start);
                }
                int windowLen = end - start + 1;
                if (minLen > windowLen) {
                    minLen = windowLen;
                    startIdx = start;
                }
            }
        }
        return startIdx == -1 ? "" : input.substring(startIdx, startIdx + minLen);
    }

    static String findSubString(String str, String pat) {
        int len1 = str.length();
        int len2 = pat.length();
        if (len1 < len2) {
            return "";
        }
        int[] hash_pat = new int[256];
        int[] hash_str = new int[256];
        for (int i = 0; i < len2; i++)
            hash_pat[pat.charAt(i)]++;

        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;
        int count = 0;
        for (int j = 0; j < len1; j++) {
            hash_str[str.charAt(j)]++;
            if (hash_str[str.charAt(j)] <= hash_pat[str.charAt(j)])
                count++;
            if (count == len2) {
                while (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)] || hash_pat[str.charAt(start)] == 0) {
                    if (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)])
                        hash_str[str.charAt(start)]--;
                    start++;
                }
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }
        if (start_index == -1) {
            return "";
        }
        return str.substring(start_index, start_index + min_len);
    }

//	private static void findWindowWithMinLengthv4(String A, String B) {
//		ArrayList<Integer> countList = new ArrayList<>();
//		Map<Integer, Integer> map = new HashMap<>();
//		Character item = null;
//		Integer excludedElement = null;
//		int minLengthOfSubstring = 0;
//		for (int i = B.length(); i < A.length(); i++) {
//			for (int j = 0; j < A.length(); j++) {
//				item = A.charAt(i);
//				map.put(item, map.getOrDefault(item, 0) + 1);
//			}
//		}
//		for (int i = 0; i < A.size(); i++) {
//			item = A.get(i);
//			map.put(item, map.getOrDefault(item, 0) + 1);
//			if ((i - B + 1) >= 0) {
//				countList.add(map.size());
//				excludedElement = A.get(i - B + 1);
//				map.put(excludedElement, map.get(excludedElement) - 1);
//				if (map.get(excludedElement) == 0) {
//					map.remove(excludedElement);
//				}
//			}
//		}
//		return countList;
//	}

}
