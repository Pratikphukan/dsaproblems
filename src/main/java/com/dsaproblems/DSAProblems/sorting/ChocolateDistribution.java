package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Collections;

public class ChocolateDistribution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int solve(ArrayList<Integer> A, int B) {
        if(A.size()==0){
            return 0;
        }
        if(B==0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        Collections.sort(A);
        for(int i = 0; i <= A.size()-B; i++){
            min = Math.min(min, A.get(i+B-1)-A.get(i));
        }
        return min;
    }
}
