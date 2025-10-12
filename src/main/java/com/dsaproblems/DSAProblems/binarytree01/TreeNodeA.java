package com.dsaproblems.DSAProblems.binarytree01;

import lombok.Data;
import lombok.NonNull;

@Data
public class TreeNodeA {

	@NonNull
	private Integer val;
	private TreeNodeA left;
	private TreeNodeA right;
	private Integer depth;

}
