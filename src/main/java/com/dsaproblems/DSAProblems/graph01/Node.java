package com.dsaproblems.DSAProblems.graph01;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node<T> {

	@NonNull
	private T label;

	private boolean isVisited;

}
