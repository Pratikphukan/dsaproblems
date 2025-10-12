package com.dsaproblems.DSAProblems.twopointers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.apache.commons.codec.binary.Hex;

public class ThreeSumZero {

    static class Triplet {

        private Integer a;

        private Integer b;

        private Integer c;

        public Triplet(Integer a, Integer b, Integer c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public Integer getC() {
            return c;
        }

        public void setC(Integer c) {
            this.c = c;
        }

    }

    public static void main(String[] args) throws Exception {
        List<Integer> input = new ArrayList<>(
                Arrays.asList(-5, 5, 5, -1, 0, -5, -1, -1, -3, 3, 2, 5, 1, 0, 5, -1));

        //[1, -4, 0, 0, 5, -5, 1, 0, -2, 4, -4, 1, -1, -4, 3, 4, -1, -1, -3]
        // [1,-4,0,0,5,-5,1,0,-2,4,-4,1,-1,-4,3,4,-1,-1,-3]
        // -1, 0, 1, 2, -1, 4
        System.out.println(threeSum(input));
        System.out.println(threeSumv1(input));
        System.out.println(threeSumv2(input));
    }

    //working code, TC->O(n^2), SC->O(1)
    private static ArrayList<ArrayList<Integer>> threeSumv2(List<Integer> input) {
        ArrayList<ArrayList<Integer>> triplets = new ArrayList<>();
        Collections.sort(input);
        int len = input.size();
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && input.get(i).equals(input.get(i - 1))) continue; // skip duplicate i
            int left = i + 1, right = len - 1, target = -input.get(i);
            while (left < right) {
                int sum = input.get(left) + input.get(right);
                if (sum == target) {
                    triplets.add(new ArrayList<>(Arrays.asList(input.get(i), input.get(left), input.get(right))));
                    left++;
                    right--;
                    while (left < right && input.get(left).equals(input.get(left - 1)))
                        left++; // skip duplicate left
                    while (left < right && input.get(right).equals(input.get(right + 1)))
                        right--; // skip duplicate right
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }

    //working code
    private static ArrayList<ArrayList<Integer>> threeSumv1(List<Integer> input) {
        ArrayList<ArrayList<Integer>> triplets = new ArrayList<>();
        Collections.sort(input);
        int len = input.size();
        for (int i = 0; i < len - 2; i++) {
            if (i == 0 || input.get(i) > input.get(i - 1)) {
                int left = i + 1, right = len - 1, target = -input.get(i);
                while (left < right) {
                    if (left > i + 1 && input.get(left).equals(input.get(left - 1))) {
                        left++;
                        continue;
                    }
                    if (right < len - 1 && input.get(right).equals(input.get(right + 1))) {
                        right--;
                        continue;
                    }
                    int sum = input.get(left) + input.get(right);
                    if (sum == target) {
                        triplets.add(new ArrayList<>(Arrays.asList(input.get(i), input.get(left), input.get(right))));
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return triplets;
    }

    public static String hash(String str) throws NoSuchAlgorithmException {
        if (str == null)
            return null;
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hashbytes);
    }

    private static ArrayList<ArrayList<Integer>> threeSum(List<Integer> nums) throws Exception {
//		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
//		ArrayList<Integer> triplet = new ArrayList<>();
//		Set<String> set = new HashSet<>();
//		Collections.sort(input);
//		String combition = null;
//		for (int i = 0; i < input.size() - 2; i++) {
//			int j = i + 1;
//			int k = input.size() - 1;
//			while (j < k) {
//				if (input.get(i) + input.get(j) + input.get(k) == 0) {
//					combition = hash(
//							String.valueOf(input.get(i)) + String.valueOf(input.get(j)) + String.valueOf(input.get(k)));
//					if (!set.contains(combition)) {
//						triplet.add(input.get(i));
//						triplet.add(input.get(j));
//						triplet.add(input.get(k));
//						ans.add(triplet);
//						triplet = new ArrayList<>();
//						set.add(combition);
//					}
//					j++;
//					k--;
//				} else if (input.get(i) + input.get(j) + input.get(k) < 0)
//					j++;
//				else
//					k--;
//			}
//		}
//		return ans;

        Collections.sort(nums);

        ArrayList<ArrayList<Integer>> pair = new ArrayList<>();
        TreeSet<String> set = new TreeSet<String>();
        ArrayList<Integer> triplets = new ArrayList<>();

        /*
         * Iterate over the array from the start and consider it as the first element
         */
        for (int i = 0; i < nums.size() - 2; i++) {

            // index of the first element in the
            // remaining elements
            int j = i + 1;

            // index of the last element
            int k = nums.size() - 1;

            while (j < k) {

                if (nums.get(i) + nums.get(j) + nums.get(k) == 0) {

                    String str = nums.get(i) + ":" + nums.get(j) + ":" + nums.get(k);

                    if (!set.contains(str)) {

                        // To check for the unique triplet
                        triplets.add(nums.get(i));
                        triplets.add(nums.get(j));
                        triplets.add(nums.get(k));
                        pair.add(triplets);
                        triplets = new ArrayList<>();
                        set.add(str);
                    }

                    // increment the second value index
                    j++;

                    // decrement the third value index
                    k--;
                } else if (nums.get(i) + nums.get(j) + nums.get(k) < 0)
                    j++;

                else
                    k--;
            }
        }
        return pair;
    }

}
