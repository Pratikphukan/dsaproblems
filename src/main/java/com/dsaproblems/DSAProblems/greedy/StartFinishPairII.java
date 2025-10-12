package com.dsaproblems.DSAProblems.greedy;

public class StartFinishPairII {

	private Integer startTime;

	private Integer endTime;

	public StartFinishPairII() {
		super();
	}

	public StartFinishPairII(Integer startTime, Integer endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "[s=" + startTime + ", e=" + endTime + "]";
	}
}
