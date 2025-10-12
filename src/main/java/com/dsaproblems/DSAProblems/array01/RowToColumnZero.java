package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowToColumnZero {

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
		input.add(new ArrayList<>(Arrays.asList(5, 6, 7, 0)));
		input.add(new ArrayList<>(Arrays.asList(0, 2, 0, 4)));
		// System.out.println(modify2DArrayv1(input));
		// System.out.println(modify2DArrayv2(input));
		System.out.println(modify2DArrayv3(input));
	}

	private static List<List<Integer>> modify2DArrayv3(List<List<Integer>> input) {
		boolean flag = false;
		int rows = input.size();
		int cols = input.get(0).size();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (input.get(i).get(j) == 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				for (int j = 0; j < cols; j++) {
					if (input.get(i).get(j) != 0) {
						input.get(i).set(j, -1);
					}
				}
			}
			flag = false;
		}
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (input.get(j).get(i) == 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				for (int j = 0; j < rows; j++) {
					if (input.get(j).get(i) != 0) {
						input.get(j).set(i, -1);
					}
				}
			}
			flag = false;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (input.get(i).get(j) == -1) {
					input.get(i).set(j, 0);
				}
			}
		}
		return input;
	}

	private static List<List<Integer>> modify2DArrayv2(List<List<Integer>> input) {
		List<List<Integer>> coordinatesWithZero = new ArrayList<>();
		int rows = input.size();
		int cols = input.get(0).size();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (input.get(i).get(j) == 0) {
					coordinatesWithZero.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}
		for (List<Integer> coordinate : coordinatesWithZero) {

		}
		return input;
	}

	private static List<List<Integer>> modify2DArrayv1(List<List<Integer>> input) {
		List<List<Integer>> coordinatesWithZero = new ArrayList<>();
		coordinatesWithZero.add(new ArrayList<>());
		coordinatesWithZero.add(new ArrayList<>());
		int rows = input.size();
		int cols = input.get(0).size();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (input.get(i).get(j) == 0) {
					coordinatesWithZero.get(0).add(i);
					coordinatesWithZero.get(1).add(j);
				}
			}
		}
		for (Integer xcoordinate : coordinatesWithZero.get(0)) {
			for (int i = 0; i < cols; i++) {
				input.get(xcoordinate).set(i, 0);
			}
		}
		for (Integer ycoordinate : coordinatesWithZero.get(1)) {
			for (int i = 0; i < rows; i++) {
				input.get(i).set(ycoordinate, 0);
			}
		}
		return input;
	}

}
