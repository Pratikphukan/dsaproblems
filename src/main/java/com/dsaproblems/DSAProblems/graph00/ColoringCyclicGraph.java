package com.dsaproblems.DSAProblems.graph00;

public class ColoringCyclicGraph {

	public static void main(String[] args) {
		int vertices = 7;
		System.out.println(colorsRequired(vertices));
	}

	private static int colorsRequired(int vertices) {
		if (vertices % 2 == 0) {
			return 2;
		}
		return 3;
	}

}
