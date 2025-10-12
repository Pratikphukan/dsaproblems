package com.dsaproblems.DSAProblems.solid.v1;

public class Crow extends Bird implements Flyable {

	FlyingBehaviour cofb = new CrowOwlFlyingBehaviour();

	@Override
	public void eat() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fly() {
		cofb.makeFly();
	}

}
