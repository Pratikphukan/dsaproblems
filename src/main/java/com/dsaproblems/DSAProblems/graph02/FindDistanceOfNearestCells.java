package com.dsaproblems.DSAProblems.graph02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindDistanceOfNearestCells {

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));
		input.add(new ArrayList<>(Arrays.asList(0, 0, 1, 1)));
		input.add(new ArrayList<>(Arrays.asList(0, 1, 1, 0)));
		findAllDistances(input);
	}

	private static List<List<Integer>> findAllDistances(List<List<Integer>> input) {
		List<List<NodeData>> matrix = new ArrayList<>();
		Set<NodeData> visitedNodes = new HashSet<>();
		Deque<NodeData> queue = new LinkedList<>();
		for (int i = 0; i < input.size(); i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < input.get(i).size(); j++) {
				NodeData node = new NodeData(i, j);
				if (input.get(i).get(j) == 1) {
					node.setDistanceFromSource(0);
					queue.addLast(node);
					visitedNodes.add(node);
				}
				matrix.get(i).add(node);
			}
		}
		while (!queue.isEmpty()) {
			NodeData node = queue.pollFirst();
			List<NodeData> neighbourList = getAllNeighbouringNodes(node, matrix);
			int distanceFromSource = node.getDistanceFromSource();
			for (NodeData neighbour : neighbourList) {
				if (!visitedNodes.contains(neighbour)) {
					neighbour.setDistanceFromSource(distanceFromSource + 1);
					queue.addLast(neighbour);
					visitedNodes.add(neighbour);
				}
			}
		}
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				input.get(i).set(j, matrix.get(i).get(j).getDistanceFromSource());
			}
		}
		return input;
	}

	private static List<NodeData> getAllNeighbouringNodes(NodeData node, List<List<NodeData>> matrix) {
		List<NodeData> neighbourList = new ArrayList<>();
		int x = node.getRow();
		int y = node.getCol();
		Integer[] rows = { -1, 0, 0, 1 };
		Integer[] cols = { 0, -1, 1, 0 };
		for (int i = 0; i < 4; i++) {
			if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
					&& y + cols[i] < matrix.get(0).size()
					&& matrix.get(x + rows[i]).get(y + cols[i]).getDistanceFromSource() == null) {
				neighbourList.add(matrix.get(x + rows[i]).get(y + cols[i]));
			}
		}
		return neighbourList;
	}

}
