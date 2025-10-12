package com.dsaproblems.DSAProblems.graph03;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FindStateOfMap {

	public static void main(String[] args) {
		List<List<Character>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList('I', 'I', 'P', 'I', 'I')));
		input.add(new ArrayList<>(Arrays.asList('I', 'P', 'P', 'I', 'I')));
		input.add(new ArrayList<>(Arrays.asList('I', 'I', 'I', 'I', 'I')));
		input.add(new ArrayList<>(Arrays.asList('I', 'P', 'I', 'I', 'P')));
		input.add(new ArrayList<>(Arrays.asList('I', 'P', 'P', 'I', 'P')));
		input.add(new ArrayList<>(Arrays.asList('I', 'I', 'I', 'I', 'P')));
		input.add(new ArrayList<>(Arrays.asList('I', 'I', 'I', 'P', 'I')));
		System.out.println(findFinalStateOfMap(input));
	}

	private static List<List<Character>> findFinalStateOfMap(List<List<Character>> input) {
		List<List<NodeData>> matrix = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < input.get(i).size(); j++) {
				NodeData node = new NodeData(i, j);
				if (input.get(i).get(j) == 'I') {
					node.setVisited(true);
					node.setData('I');
				}
				matrix.get(i).add(node);
			}
		}
		System.out.println(matrix);
		int firstRow = 0;
		int lastRow = input.size() - 1;
		for (int i = 0; i < input.get(0).size(); i++) {
			if (!matrix.get(firstRow).get(i).isVisited()) {
				depthFirstSearch(matrix.get(firstRow).get(i), matrix);
			}
			if (!matrix.get(lastRow).get(i).isVisited()) {
				depthFirstSearch(matrix.get(lastRow).get(i), matrix);
			}
		}
		int firstCol = 0;
		int lastCol = input.get(0).size() - 1;
		for (int i = 0; i < input.get(0).size(); i++) {
			if (!matrix.get(i).get(firstCol).isVisited()) {
				depthFirstSearch(matrix.get(i).get(firstCol), matrix);
			}
			if (!matrix.get(i).get(lastCol).isVisited()) {
				depthFirstSearch(matrix.get(i).get(lastCol), matrix);
			}
		}
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				if (!matrix.get(i).get(j).isVisited()) {
					input.get(i).set(j, 'I');
				}
			}
		}
		return input;
	}

	private static void depthFirstSearch(NodeData start, List<List<NodeData>> matrix) {
		Deque<NodeData> stack = new ArrayDeque<>();
		start.setVisited(true);
		start.setData('P');
		stack.addFirst(start);
		while (!stack.isEmpty()) {
			NodeData node = stack.removeFirst();
			List<NodeData> childList = getAllNeighbouringNodes1(node, matrix);
			for (NodeData child : childList) {
				if (!child.isVisited()) {
					child.setVisited(true);
					child.setData('P');
					stack.addFirst(child);
				}
			}
		}
	}

	private static List<NodeData> getAllNeighbouringNodes1(NodeData node, List<List<NodeData>> matrix) {
		List<NodeData> neighbourList = new ArrayList<>();
		int x = node.getRow();
		int y = node.getCol();
		Integer[] rows = { -1, 0, 0, 1, -1, -1, 1, 1 };
		Integer[] cols = { 0, -1, 1, 0, -1, 1, -1, 1 };
		for (int i = 0; i < 8; i++) {
			if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
					&& y + cols[i] < matrix.get(0).size() && !matrix.get(x + rows[i]).get(y + cols[i]).isVisited()) {
				neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
			}
		}
		return neighbourList;
	}

}
