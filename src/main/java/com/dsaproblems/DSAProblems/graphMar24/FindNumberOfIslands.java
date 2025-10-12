package com.dsaproblems.DSAProblems.graphMar24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FindNumberOfIslands {

	static class NodeData {
		private Integer row;
		private Integer col;
		private boolean visited;

		public NodeData(Integer row, Integer col) {
			this.row = row;
			this.col = col;
		}

		public boolean isVisited() {
			return visited;
		}

		public NodeData setVisited(boolean visited) {
			this.visited = visited;
			return this;
		}

		public Integer getRow() {
			return row;
		}

		public Integer getCol() {
			return col;
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 1)));
		input.add(new ArrayList<>(Arrays.asList(0, 1, 0, 1, 0)));
		input.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1)));
		input.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)));
		input.add(new ArrayList<>(Arrays.asList(1, 0, 1, 1, 1)));
		System.out.println(findAllIslands(input));
	}

	private static int findAllIslands(List<List<Integer>> input) {
		List<List<Integer>> matrix = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < input.get(i).size(); j++) {
				if (input.get(i).get(j).equals(0)) {
					matrix.get(i).add(-1);
				} else {
					matrix.get(i).add(null);
				}
			}
		}
		Deque<NodeData> stack = new LinkedList<>();
		int ans = 0;
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				if (matrix.get(i).get(j) == null) {
					ans++;
					matrix.get(i).set(j, 1);
					stack.addFirst(new NodeData(i, j).setVisited(true));
					while (!stack.isEmpty()) {
						NodeData node = stack.removeFirst();
						List<NodeData> childList = getAllNeighbouringNodes(node, matrix);
						for (NodeData child : childList) {
							if (!child.isVisited()) {
								matrix.get(child.getRow()).set(child.getCol(), 1);
								stack.addFirst(child.setVisited(true));
							}
						}
					}
				}
			}
		}
		return ans;
	}

	private static List<NodeData> getAllNeighbouringNodes(NodeData node, List<List<Integer>> matrix) {
		List<NodeData> neighbourList = new ArrayList<>();
		int x = node.getRow();
		int y = node.getCol();
		Integer[] rows = { -1, 0, 0, 1, -1, -1, 1, 1 };
		Integer[] cols = { 0, -1, 1, 0, -1, 1, -1, 1 };
		for (int i = 0; i < 8; i++) {
			if (x + rows[i] >= 0 && x + rows[i] < matrix.size() && y + cols[i] >= 0
					&& y + cols[i] < matrix.get(0).size() && matrix.get(x + rows[i]).get(y + cols[i]) == null) {
				neighbourList.add(new NodeData(x + rows[i], y + cols[i]));
			}
		}
		return neighbourList;
	}

}
