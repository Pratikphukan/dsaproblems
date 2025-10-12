package com.dsaproblems.DSAProblems.hashing02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.cache.interceptor.AbstractCacheResolver;

public class Unique2DPoints {

	static class TwoDCoordinates {

		private Integer x;

		private Integer y;

		public TwoDCoordinates(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList(5, 6)));
		input.add(new ArrayList<>(Arrays.asList(2, 8)));
		input.add(new ArrayList<>(Arrays.asList(-1, -1)));
		input.add(new ArrayList<>(Arrays.asList(2, -3)));
		input.add(new ArrayList<>(Arrays.asList(2, 8)));
		input.add(new ArrayList<>(Arrays.asList(7, 7)));
		input.add(new ArrayList<>(Arrays.asList(2, -3)));
		System.out.println(findUniquePointsInArray(input));
		System.out.println(findUniquePointsInArrayv1(input));

		// 1,7,11,8,11,7,1->0,2,4,6
		ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 7, 11, 8, 11, 7, 1));
		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
		B.add(new ArrayList<>(Arrays.asList(0, 2, 4, 6)));
		System.out.println(solve(A, B));

	}

	private static int findUniquePointsInArrayv1(List<List<Integer>> input) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		Map<Integer, Integer> frequencyMap = null;
		for (List<Integer> coordinates : input) {
			if (!map.containsKey(coordinates.get(0))) {
				frequencyMap = new HashMap<>();
				frequencyMap.put(coordinates.get(1), frequencyMap.getOrDefault(coordinates.get(1), 0) + 1);
				map.put(coordinates.get(0), frequencyMap);
			} else {
				frequencyMap = map.get(coordinates.get(0));
				frequencyMap.put(coordinates.get(1), frequencyMap.getOrDefault(coordinates.get(1), 0) + 1);
			}
		}
		int count = 0;
		for (Map.Entry<Integer, Map<Integer, Integer>> entry : map.entrySet()) {
			count += entry.getValue().size();
		}
		return count;
	}

	public static ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
		ArrayList<Integer> ans = new ArrayList<>();
		Map<Integer, Integer> map = null;
		for (ArrayList<Integer> query : B) {
			map = new HashMap<>();
			if (Objects.equals(query.get(1) - query.get(1), query.get(3) - query.get(2))) {
				for (int i = query.get(0); i <= query.get(1); i++) {
					map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);
				}
				for (int i = query.get(2); i <= query.get(3); i++) {
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

	private static int findUniquePointsInArray(List<List<Integer>> input) {
		Map<TwoDCoordinates, Integer> map = new HashMap<>();
		TwoDCoordinates point = null;
		for (List<Integer> coordinates : input) {
			point = new TwoDCoordinates(coordinates.get(0), coordinates.get(1));
			map.put(point, map.getOrDefault(point, 0) + 1);
		}
		return 0;
	}

}
