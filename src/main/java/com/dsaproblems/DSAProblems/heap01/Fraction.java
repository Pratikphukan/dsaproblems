package com.dsaproblems.DSAProblems.heap01;

public class Fraction implements Comparable<Fraction> {

	private Integer numerator;

	private Integer denominator;

	private Double value;

	public Fraction(Integer numerator, Integer denominator, Double value) {
		super();
		this.numerator = numerator;
		this.denominator = denominator;
		this.value = value;
	}

	public Integer getNumerator() {
		return numerator;
	}

	public void setNumerator(Integer numerator) {
		this.numerator = numerator;
	}

	public Integer getDenominator() {
		return denominator;
	}

	public void setDenominator(Integer denominator) {
		this.denominator = denominator;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[" + numerator + ", " + denominator + ", " + value + "]";
	}

	@Override
	public int compareTo(Fraction fraction) {
		return Double.compare(this.value, fraction.value);
	}

}
