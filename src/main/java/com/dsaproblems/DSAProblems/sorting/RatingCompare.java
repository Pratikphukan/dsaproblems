package com.dsaproblems.DSAProblems.sorting;

import java.util.Comparator;

public class RatingCompare implements Comparator<Movie> {

	@Override
	public int compare(Movie movie1, Movie movie2) {
		if (movie1.getRating() < movie2.getRating())
			return -1;
		if (movie1.getRating() > movie2.getRating())
			return 1;
		else
			return 0;
	}

}
