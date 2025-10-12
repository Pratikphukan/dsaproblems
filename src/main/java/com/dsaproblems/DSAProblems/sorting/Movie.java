package com.dsaproblems.DSAProblems.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Movie implements Comparable<Movie> {

	private String name;

	private double rating;

	private int year;

	@Override
	public int compareTo(Movie movie) {
		return this.year - movie.year;
		// return movie.year - this.year;
	}

}
