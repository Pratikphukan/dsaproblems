package com.dsaproblems.DSAProblems.subarrays;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CompareSortedSubarrays {

    @Getter
    static class Node {
        private int value;

        private int frequency;

        private List<Integer> indices;

        public Node(int value) {
            this.value = value;
            this.frequency = 1;
            this.indices = new ArrayList<>();
        }


    }

    //the array has only non negative numbers
    public static void main(String[] args) {
        // 1,7,11,8,11,7,1->0,2,4,6

        // 88,538,1093,1508,2086,2501,3090,3589,4052,4508,5018,5547,6039,6506,7086,7589,8097,8545,9081,9531,10011,10540,11055,11599,12057,12503,13092,13534,14081,14585,15043,15520,16087,16550,17095,17543,18003,18555,19001,19525,20047,20578,21015,21512,22071,22514,23053,23589,24062,24567,25055,25560,26071,26533,27006,27539,28032,28555,29054,29509,30009,30598,31082,31538,32076,32527,33022,33516,34023,34550,35062,35527,36003,36583,37019,37596,38000,38562,39064,39502,40058,40501,41053,41515,42010,42537,43046,43586,44031,44582,45010,45568,46050,46567,47058,47524,48076,48534,49041,49570,50007,50535,51016,51516,52084,52563,53030,53588,54091,54522,55022,55504,56007,56561,57099,57587,58052,58538,59001,59595,60070,60593,61029,61563,62080,62560,63001,63575,64053,64529,65029,65574,66074,66575,67065,67555,68092,68558,69020,69531,70031,70518,71088,71514,72083,72526,73019,73556,74082,74545,75009,75566,76046,76594,77083,77565,78036,78521,79028,79594,80085,80574,81098,81519,82004,82548,83059,83513,84013,84576,85029,85594,86005,86555,87038,87557,88069,88568,89008,89559,90011,90579,91067,91573,92030,92591,93084,93575,94017,94515,95011,95563,96028,96527,97060,97590,98010,98551,99003,99566
//		ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 7, 11, 8, 11, 7, 1));
//		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
//		B.add(new ArrayList<>(Arrays.asList(0, 2, 4, 6)));

//		ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5, 3, 4, 4));
//		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
//		B.add(new ArrayList<>(Arrays.asList(0, 1, 2, 3)));

//        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 7, 11, 8, 11, 7, 1));
//        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
//        B.add(new ArrayList<>(Arrays.asList(0, 2, 4, 6)));

        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(4, 3, 2, 1));
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        B.add(new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
        B.add(new ArrayList<>(Arrays.asList(2, 2, 3, 3)));
        B.add(new ArrayList<>(Arrays.asList(1, 3, 0, 0)));
        B.add(new ArrayList<>(Arrays.asList(3, 3, 1, 2)));
        B.add(new ArrayList<>(Arrays.asList(1, 2, 2, 3)));

        System.out.println(getSameSortedSubarraysv1(A, B));
        System.out.println(getSameSortedSubarraysv2(A, B));
        System.out.println(getSameSortedSubarraysv3(A, B));
        System.out.println(getSameSortedSubarraysv4(A, B));
        System.out.println(getSameSortedSubarraysv5(A, B));
        System.out.println(getSameSortedSubarraysv6(A, B));

        System.out.println(getSameSortedSubarraysv7(A, B));
        System.out.println(getSameSortedSubarraysv8(A, B));
    }

    private static ArrayList<Integer> getSameSortedSubarraysv8(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        // Random 64-bit labels per distinct value (two independent hashes)
        HashMap<Integer, Long> R1 = new HashMap<>();
        HashMap<Integer, Long> R2 = new HashMap<>();

        long[] H1 = new long[n + 1];
        long[] H2 = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int v = A.get(i);
            Long a = R1.get(v);
            if (a == null) {
                long x = rnd.nextLong();
                if (x == 0) x = 1;
                long y = rnd.nextLong();
                if (y == 0) y = 1;
                R1.put(v, x);
                R2.put(v, y);
                a = x;
            }
            long b = R2.get(v);
            H1[i + 1] = H1[i] + a;
            H2[i + 1] = H2[i] + b;
        }

        ArrayList<Integer> ans = new ArrayList<>(B.size());
        for (ArrayList<Integer> q : B) {
            int l1 = q.get(0), r1 = q.get(1);
            int l2 = q.get(2), r2 = q.get(3);

            // different lengths => cannot be same multiset
            if ((r1 - l1) != (r2 - l2)) {
                ans.add(0);
                continue;
            }

            long a1 = H1[r1 + 1] - H1[l1], b1 = H2[r1 + 1] - H2[l1];
            long a2 = H1[r2 + 1] - H1[l2], b2 = H2[r2 + 1] - H2[l2];
            ans.add((a1 == a2 && b1 == b2) ? 1 : 0);
        }
        return ans;
    }

    // working but uses very high space, throws OutOfMemoryError
    private static ArrayList<Integer> getSameSortedSubarraysv6(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        List<Map<Integer, Integer>> prefixFreq = new ArrayList<>();
        for (int i = 0; i <= A.size(); i++) {
            if (i == 0) {
                prefixFreq.add(new HashMap<>());
            } else {
                Map<Integer, Integer> prevMap = new HashMap<>(prefixFreq.get(i - 1));
                int val = A.get(i - 1);
                prevMap.put(val, prevMap.getOrDefault(val, 0) + 1);
                prefixFreq.add(prevMap);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            int start1 = query.get(0), end1 = query.get(1);
            int start2 = query.get(2), end2 = query.get(3);
            if (end1 - start1 != end2 - start2) {
                ans.add(0);
                continue;
            }
            Map<Integer, Integer> freqMap1 = new HashMap<>();
            Map<Integer, Integer> freqMap2 = new HashMap<>();
            Map<Integer, Integer> freq1 = prefixFreq.get(start1);
            Map<Integer, Integer> freq2 = prefixFreq.get(end1 + 1);
            Map<Integer, Integer> freq3 = prefixFreq.get(start2);
            Map<Integer, Integer> freq4 = prefixFreq.get(end2 + 1);
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : freq2.entrySet()) {
                int key = entry.getKey();
                count = entry.getValue() - freq1.getOrDefault(key, 0);
                if (count > 0)
                    freqMap1.put(key, count);
            }
            for (Map.Entry<Integer, Integer> entry : freq4.entrySet()) {
                int key = entry.getKey();
                count = entry.getValue() - freq3.getOrDefault(key, 0);
                if (count > 0)
                    freqMap2.put(key, count);

            }
            if (freqMap1.equals(freqMap2)) {
                ans.add(1);
            } else {
                ans.add(0);
            }
        }
        return ans;
    }


    // working but uses very high space, throws OutOfMemoryError same as getSameSortedSubarraysv6
    private static ArrayList<Integer> getSameSortedSubarraysv7(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        List<Map<Integer, Integer>> indexFreq = new ArrayList<>();
        Map<Integer, Integer> temp = new HashMap<>();
        Set<Integer> keys = new HashSet<>();
        for (int num : A) {
            temp.put(num, 0);
            keys.add(num);
        }
        indexFreq.add(temp);
        for (int num : A) {
            Map<Integer, Integer> valueFreq = new HashMap<>(temp);
            valueFreq.put(num, valueFreq.getOrDefault(num, 0) + 1);
            indexFreq.add(valueFreq);
            temp = valueFreq;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            int start1 = query.get(0), end1 = query.get(1);
            int start2 = query.get(2), end2 = query.get(3);
            if (end1 - start1 != end2 - start2) {
                ans.add(0);
                continue;
            }
            Map<Integer, Integer> s1 = indexFreq.get(start1);
            Map<Integer, Integer> e1 = indexFreq.get(end1 + 1);
            Map<Integer, Integer> s2 = indexFreq.get(start2);
            Map<Integer, Integer> e2 = indexFreq.get(end2 + 1);
            boolean check = true;
            for (int key : keys) {
                if (e1.get(key) - s1.get(key) != e2.get(key) - s2.get(key)) {
                    ans.add(0);
                    check = false;
                    break;
                }
            }
            if (check) ans.add(1);
        }
        return ans;
    }

    private static ArrayList<Integer> getSameSortedSubarraysv5(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        // Map each unique value to an index
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        int idx = 0;
        for (int val : A) {
            if (!valueToIndex.containsKey(val)) {
                valueToIndex.put(val, idx++);
            }
        }
        int n = A.size(), m = valueToIndex.size();
        int[][] freq = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                freq[i + 1][j] = freq[i][j];
            }
            int mappedIdx = valueToIndex.get(A.get(i));
            freq[i + 1][mappedIdx]++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            int start1 = query.get(0), end1 = query.get(1);
            int start2 = query.get(2), end2 = query.get(3);
            if (end1 - start1 != end2 - start2) {
                ans.add(0);
                continue;
            }
            boolean same = true;
            for (int j = 0; j < m; j++) {
                int f1 = freq[end1 + 1][j] - freq[start1][j];
                int f2 = freq[end2 + 1][j] - freq[start2][j];
                if (f1 != f2) {
                    same = false;
                    break;
                }
            }
            ans.add(same ? 1 : 0);
        }
        return ans;
    }

    //working code, but uses O(n) space
    //Example (length 2): [5, 5] vs [1, 7] → 25+25=50 and 1+49=50. Your code would say “same”, but they aren’t.
    private static ArrayList<Integer> getSameSortedSubarraysv4(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        List<Integer> prefix = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if (i == 0) {
                prefix.add(A.get(0) * A.get(0));
            } else {
                prefix.add(prefix.get(i - 1) + A.get(i) * A.get(i));
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            int start1 = query.get(0);
            int end1 = query.get(1);
            int start2 = query.get(2);
            int end2 = query.get(3);

            if (end1 - start1 != end2 - start2) {
                ans.add(0);
                continue;
            }
            if (start1 == 0) {
                if (start2 == 0) {
                    if (prefix.get(end1) == prefix.get(end2)) {
                        ans.add(1);
                    } else
                        ans.add(0);
                } else {
                    if (prefix.get(end1) == prefix.get(end2) - prefix.get(start2 - 1)) {
                        ans.add(1);
                    } else
                        ans.add(0);
                }
            } else {
                if (start2 == 0) {
                    if (prefix.get(end1) - prefix.get(start1 - 1) == prefix.get(end2)) {
                        ans.add(1);
                    } else
                        ans.add(0);
                } else {
                    if ((prefix.get(end1) - prefix.get(start1 - 1) == prefix.get(end2) - prefix.get(start2 - 1))) {
                        ans.add(1);
                    } else
                        ans.add(0);
                }
            }
        }
        return ans;
    }

    //working code, just throws TLE
    private static ArrayList<Integer> getSameSortedSubarraysv3(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
//        Map<Integer, List<Integer>> valIdxMap = new HashMap<>();
//        for (int i = 0; i < A.size(); i++) {
//            int val = A.get(i);
//            if (!valIdxMap.containsKey(val)) {
//                valIdxMap.put(val, new ArrayList<>());
//            }
//            valIdxMap.get(val).add(i);
//        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> query : B) {
            int start1 = query.get(0);
            int end1 = query.get(1);
            int start2 = query.get(2);
            int end2 = query.get(3);

            if (end1 - start1 != end2 - start2) {
                ans.add(0);
                continue;
            }

            Map<Integer, Integer> freqMap1 = new HashMap<>();
            Map<Integer, Integer> freqMap2 = new HashMap<>();

            for (int i = start1; i <= end1; i++) {
                freqMap1.put(A.get(i), freqMap1.getOrDefault(A.get(i), 0) + 1);
            }
            for (int i = start2; i <= end2; i++) {
                freqMap2.put(A.get(i), freqMap2.getOrDefault(A.get(i), 0) + 1);
            }

            if (freqMap1.equals(freqMap2)) {
                ans.add(1);
            } else {
                ans.add(0);
            }
        }
        return ans;
    }

    public static long hash(Integer input, Integer idx) {
//		long hashValue = input.longValue() * 10000000007l; // Multiply input by a prime number
//		hashValue = hashValue ^ 0xDEADBEEF; // XOR with a constant value, can be negative
//		hashValue = Math.abs(hashValue);
//		return hashValue;

        int mod = 1000000007;
        int a = input % mod;
        int b = ((int) Math.pow(29, idx)) % mod;
        long val = a * b;
        return val % mod;

    }

    private static ArrayList<Integer> getSameSortedSubarraysv2(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> ans = new ArrayList<>();
        long subarraySum = 0;
        for (ArrayList<Integer> query : B) {
            if (Objects.equals(query.get(1) - query.get(0), query.get(3) - query.get(2))) {
                subarraySum = 0;
                for (int i = query.get(0); i <= query.get(1); i++) {
                    subarraySum += hash(A.get(i), i);
                }
                for (int i = query.get(2); i <= query.get(3); i++) {
                    subarraySum -= hash(A.get(i), i);
                    if (subarraySum < 0) {
                        break;
                    }
                }
                ans.add(subarraySum == 0 ? 1 : 0);
            } else {
                ans.add(0);
            }
        }
        return ans;
    }

    public static String byteArrToHexString(byte[] hash) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte val : hash) {
            strBuilder.append(String.format("%02x", val & 0xff)); // 0xff->11111111 in binary
        }
        return strBuilder.toString();
    }

    // working code but fails when input is very large
    private static ArrayList<Integer> getSameSortedSubarraysv1(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) { // array
        // of
        // positive
        // numbers
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = null;
        int subarraySum = 0;
        for (ArrayList<Integer> query : B) {
            map = new HashMap<>();
            if (Objects.equals(query.get(1) - query.get(0), query.get(3) - query.get(2))) {
                subarraySum = 0;
                for (int i = query.get(0); i <= query.get(1); i++) {
                    subarraySum += A.get(i);
                    map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);
                }
                for (int i = query.get(2); i <= query.get(3); i++) {
                    subarraySum -= A.get(i);
                    if (subarraySum < 0) {
                        ans.add(0);
                        break;
                    }
                    if (!map.containsKey(A.get(i))) {
                        ans.add(0);
                        break;
                    } else {
                        map.put(A.get(i), map.get(A.get(i)) - 1);
                        if (map.get(A.get(i)) == 0) {
                            map.remove(A.get(i));
                        }
                    }
                }
                if (map.isEmpty()) {
                    ans.add(1);
                }
            } else {
                ans.add(0);
            }
        }
        return ans;
    }

}
