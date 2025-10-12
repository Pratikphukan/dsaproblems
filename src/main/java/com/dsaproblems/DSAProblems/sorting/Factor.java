package com.dsaproblems.DSAProblems.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Factor implements Comparable<Integer> {

	private Integer num;

	@Override
	public int compareTo(Integer item) {
		int currFactors = this.getCountOfFactors();
		Factor compareItem = new Factor(item);
		int compareItemFactors = compareItem.getCountOfFactors();
		if (currFactors < compareItemFactors) {
			return 1;
		} else if (currFactors > compareItemFactors) {
			return -1;
		} else {
			if (this.num == compareItem.num)
				return 0;
			else if (this.num > compareItem.num)
				return 1; // b should come before a, swap happens
			else
				return -1; // a should come before b, swap does not happen
			// return 0;
		}

	}

	public int getCountOfFactors() {
		int count = 0;
		for (int i = 1; i * i <= this.num; i++) {
			if (this.num % i == 0) {
				count += 2; // 1 and the number itself are factors
			}
			if (i * i == this.num) {
				count -= 1;
			}
		}
		return count;
	}

}
