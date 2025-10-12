package com.dsaproblems.DSAProblems.heap02;

import java.util.Objects;

public class PairSum {

	private Integer sum;

	private Integer idx1;

	private Integer idx2;

	public PairSum(Integer idx1, Integer idx2, Integer sum) {
		this.sum = sum;
		this.idx1 = idx1;
		this.idx2 = idx2;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getIdx1() {
		return idx1;
	}

	public void setIdx1(Integer idx1) {
		this.idx1 = idx1;
	}

	public Integer getIdx2() {
		return idx2;
	}

	public void setIdx2(Integer idx2) {
		this.idx2 = idx2;
	}

	@Override
	public String toString() {
		return "[" + sum + ", " + idx1 + ", " + idx2 + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idx1, idx2, sum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairSum other = (PairSum) obj;
		return Objects.equals(idx1, other.idx1) && Objects.equals(idx2, other.idx2);
	}
}
