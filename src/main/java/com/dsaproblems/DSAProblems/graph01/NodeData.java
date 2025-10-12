package com.dsaproblems.DSAProblems.graph01;

import lombok.Data;
import lombok.NonNull;

@Data
public class NodeData {

	@NonNull
	private Integer data;

	private boolean isVisited;

}
