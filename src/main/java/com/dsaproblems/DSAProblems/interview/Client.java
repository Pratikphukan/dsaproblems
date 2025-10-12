package com.dsaproblems.DSAProblems.interview;

import com.dsaproblems.DSAProblems.interview.v1.ChessStrategy;
import com.dsaproblems.DSAProblems.interview.v1.GamePlayStrategy;

public class Client {

    public static void main(String[] args) {
        GamePlayStrategy g = new ChessStrategy();
        g.playGame();
    }
}
