package com.dsaproblems.DSAProblems.graph03;

import lombok.Data;

@Data
class NodeData {

	private Integer row;
	private Integer col;
	private boolean isVisited;
	private Character data;

	public NodeData(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return row + "," + col + "," + data + "," + isVisited;
	}

}
