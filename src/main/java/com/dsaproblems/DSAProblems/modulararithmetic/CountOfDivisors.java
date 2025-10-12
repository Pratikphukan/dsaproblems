package com.dsaproblems.DSAProblems.modulararithmetic;

import java.util.ArrayList;

public class CountOfDivisors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public ArrayList<Integer> solve(ArrayList<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < A.size(); i++){
            ans.add(countOfDivisors(A.get(i)));
        }
        return ans;
    }
    public int countOfDivisors(int B){
        int count = 0;
        for(int i = 1; i*i<=B; i++){
            if(B%i==0){
                count+=2;
            }
            if(i*i==B){
                count-=1;
            }
        }
        return count;
    }

}
