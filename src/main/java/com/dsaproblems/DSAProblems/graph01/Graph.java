package com.dsaproblems.DSAProblems.graph01;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

	private static final int MAX_VERTICES = 10;

	private List<Node<T>> nodes;

	private Integer[][] adjMatrix;

	public Graph() {
		this.nodes = new ArrayList<>();
		this.adjMatrix = new Integer[MAX_VERTICES][MAX_VERTICES];
	}

	public void addNode(T t) {
		nodes.add(new Node<T>(t));
	}

	public Node<T> getNode(Integer idx) {
		return nodes.get(idx);
	}

	public List<Node<T>> getNodes() {
		return nodes;
	}

	public Integer getCell(int row, int col) {
		return adjMatrix[row][col];
	}

	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}

	public Integer getAdjUnvisitedVertex(Integer idx) {
		for (int i = 0; i < this.nodes.size(); i++) {
			if (this.getCell(idx, i) != null && !this.getNode(i).isVisited()) {
				return i;
			}
		}
		return -1;
	}

}
