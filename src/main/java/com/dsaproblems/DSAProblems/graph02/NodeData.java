package com.dsaproblems.DSAProblems.graph02;

import lombok.Data;

@Data
public class NodeData {

	private Integer row;
	private Integer col;
	private Integer distanceFromSource;

	public NodeData(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return row + "," + col + "," + distanceFromSource;
	}

}
