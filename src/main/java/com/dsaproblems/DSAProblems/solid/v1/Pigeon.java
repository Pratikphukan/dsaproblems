package com.dsaproblems.DSAProblems.solid.v1;

public class Pigeon extends Bird implements Flyable, Dancer {

	private FlyingBehaviour psfb;

	public Pigeon(FlyingBehaviour psfb) {
		super();
		this.psfb = psfb;
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dance() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fly() {
		psfb.makeFly();
	}

}
