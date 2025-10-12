package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class CountPairsWithGivenSumII {

    public static void main(String[] args) {
        //2,2,3,4,4,5,6,7,10->8
        //1, 1, 1, 1, 1->2
        //2,3,3,5,7,7,8,9,9,10,10->11
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 3, 5, 7, 7, 8, 9, 9, 10, 10)); //non distinct and sorted integers
        int targetSum = 11;
        System.out.println(findCountPairsWithGivenSumv1(input, targetSum)); //for unique pairs, this will work
        System.out.println(findCountPairsWithGivenSumv2(input, targetSum));
        System.out.println(findCountPairsWithGivenSumv3(input, targetSum));
        System.out.println(findCountPairsWithGivenSumv4(input, targetSum));
        System.out.println(findCountPairsWithGivenSumv5(input, targetSum));
    }

    //working code
    private static int findCountPairsWithGivenSumv5(List<Integer> nums, int target) {
        int MOD = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        long count = 0;
        for (int num : nums) {
            int complement = target - num;
            if (map.getOrDefault(num, 0) > 0 && map.getOrDefault(complement, 0) > 0) {
                if (num == complement && map.get(num) < 2) continue;
                if (num == complement) {
                    long freq = map.get(num);
                    count = (count + (freq * (freq - 1) / 2) % MOD) % MOD;
                    map.remove(num);
                } else {
                    long freq1 = map.get(num);
                    long freq2 = map.get(complement);
                    count = (count + (freq1 * freq2) % MOD) % MOD;
                    map.remove(num);
                    map.remove(complement);
                }
            }
        }
        return (int) count;
    }

    //if you use integer division / 2 after modulo can lead to wrong results
    // due to operator precedence and integer division
    //working code
    private static int findCountPairsWithGivenSumv4(List<Integer> nums, int target) {
        int MOD = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        long count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (visited.contains(num)) continue;
            if (map.containsKey(complement)) {
                if (num == complement) {
                    long freq = map.get(num);
                    count = (count + (freq * (freq - 1) / 2) % MOD) % MOD;
                } else if (!visited.contains(complement)) {
                    long freq1 = map.get(num);
                    long freq2 = map.get(complement);
                    count = (count + (freq1 * freq2) % MOD) % MOD;
                }
            }
            visited.add(num);
        }
        return (int) count;
    }

    //working code
    private static int findCountPairsWithGivenSumv3(List<Integer> A, int B) {
        int i = 0, j = A.size() - 1, mod = 1_000_000_007;
        long ans = 0;
        while (i < j) {
            int left = A.get(i), right = A.get(j);
            if (left + right == B) {
                if (left == right) {
                    long cnt = j - i + 1;
                    ans = (ans + cnt * (cnt - 1) / 2) % mod;
                    break;
                } else {
                    int cntLeft = 1, cntRight = 1;
                    while (i + cntLeft < j && A.get(i + cntLeft) == left) cntLeft++;
                    while (j - cntRight > i && A.get(j - cntRight) == right) cntRight++;
                    ans = (ans + (long) cntLeft * cntRight) % mod;
                    i += cntLeft;
                    j -= cntRight;
                }
            } else if (left + right > B) {
                j--;
            } else {
                i++;
            }
        }
        return (int) ans;
    }

    //O(n) time and O(1) space
    //For unsorted input, you could use a HashSet for O(n) time.
    private static int findCountPairsWithGivenSumv2(List<Integer> input, int targetSum) {
        int left = 0, right = input.size() - 1;
        int mod = 1000000007;
        long count = 0;
        while (left < right) {
            int sum = input.get(left) + input.get(right);
            if (sum == targetSum) {
                if (input.get(left).equals(input.get(right))) {
                    long range = right - left + 1;
                    count += (range * (range - 1) / 2) % mod;
                    break;
                } else {
                    long a = 1, b = 1;
                    while (left + 1 < right && input.get(left).equals(input.get(left + 1))) {
                        left++;
                        a++;
                    }
                    while (left < right - 1 && input.get(right).equals(input.get(right - 1))) {
                        right--;
                        b++;
                    }
                    count += (a * b) % mod;
                    left++;
                    right--;
                }
            } else if (sum > targetSum) {
                right--;
            } else {
                left++;
            }
        }
        return (int) count % mod;
    }

    private static int findCountPairsWithGivenSumv1(List<Integer> input, int targetSum) {
        int count = 0;
        int low = 0, high = input.size() - 1;
        while (low < high) {
            int sum = input.get(low) + input.get(high);
            if (sum == targetSum) {
                count++;
                int leftVal = input.get(low), rightVal = input.get(high);
                while (low < high && input.get(low) == leftVal) low++;
                while (low < high && input.get(high) == rightVal) high--;
            } else if (sum > targetSum) {
                high--;
            } else {
                low++;
            }
        }
        return count;
    }
}
