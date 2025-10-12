package com.dsaproblems.DSAProblems.graphMar24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindTimeOfRottenOranges {

	static class NodeData {
		private Integer row;
		private Integer col;
		private Integer distanceFromSource;

		public NodeData(Integer row, Integer col) {
			this.row = row;
			this.col = col;
		}

		public Integer getDistanceFromSource() {
			return distanceFromSource;
		}

		public void setDistanceFromSource(Integer distanceFromSource) {
			this.distanceFromSource = distanceFromSource;
		}

		public Integer getRow() {
			return row;
		}

		public Integer getCol() {
			return col;
		}

		@Override
		public String toString() {
			return "[" + row + ", " + col + ", " + distanceFromSource + "]";
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
		input.add(new ArrayList<>(Arrays.asList(1, 1, 0)));
		input.add(new ArrayList<>(Arrays.asList(0, 1, 1)));

		System.out.println(findMinimumTimeUntilNoFreshOrangeLeft(input));
	}

	private static int findMinimumTimeUntilNoFreshOrangeLeft(List<List<Integer>> input) {
		List<List<Integer>> matrix = new ArrayList<>();
		List<NodeData> sourceNodes = new ArrayList<>();
		NodeData sourceNode = null;
		for (int i = 0; i < input.size(); i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < input.get(i).size(); j++) {
				if (input.get(i).get(j).equals(0)) {
					matrix.get(i).add(-1);
				} else if (input.get(i).get(j).equals(2)) {
					matrix.get(i).add(0);
					sourceNode = new NodeData(i, j);
					sourceNode.setDistanceFromSource(0);
					sourceNodes.add(sourceNode);
				} else {
					matrix.get(i).add(null);
				}
			}
		}
		Set<NodeData> visitedNodes = new HashSet<>();
		Deque<NodeData> queue = new LinkedList<>();
		for (NodeData srcNode : sourceNodes) {
			queue.addLast(srcNode);
			visitedNodes.add(sourceNode);
		}
		while (!queue.isEmpty()) {
			NodeData node = queue.pollFirst();
			List<NodeData> neighbourList = getAllNeighbouringNodes(node.getRow(), node.getCol(), matrix);
			int distanceFromSource = node.getDistanceFromSource();
			for (NodeData neighbour : neighbourList) {
				if (!visitedNodes.contains(neighbour)) {
					neighbour.setDistanceFromSource(distanceFromSource + 1);
					matrix.get(neighbour.getRow()).set(neighbour.getCol(), distanceFromSource + 1);
					queue.addLast(neighbour);
					visitedNodes.add(neighbour);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				if (matrix.get(i).get(j) == null) {
					return -1;
				}
				ans = Math.max(ans, matrix.get(i).get(j));
			}
		}
		return ans;
	}

	private static List<NodeData> getAllNeighbouringNodes(Integer x, Integer y, List<List<Integer>> matrix) {
		List<NodeData> neighbourList = new ArrayList<>();
		if ((x - 1) >= 0 && matrix.get(x - 1).get(y) == null) {
			neighbourList.add(new NodeData(x - 1, y));
		}
		if ((y - 1) >= 0 && matrix.get(x).get(y - 1) == null) {
			neighbourList.add(new NodeData(x, y - 1));
		}
		if ((x + 1) < matrix.size() && matrix.get(x + 1).get(y) == null) {
			neighbourList.add(new NodeData(x + 1, y));
		}
		if ((y + 1) < matrix.get(0).size() && matrix.get(x).get(y + 1) == null) {
			neighbourList.add(new NodeData(x, y + 1));
		}
		return neighbourList;
	}
}
